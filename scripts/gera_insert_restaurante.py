import pandas as pd
from slugify import slugify
from db import get_tipos_de_cozinha
import warnings

warnings.filterwarnings('ignore')

# Busca tipos_de_cozinha no banco
tipos_de_cozinha = get_tipos_de_cozinha()

# Lê os dados dos restaurantes
file = 'Restaurantes - Dados.csv'
df = pd.read_csv(file)

# Remove caracteres especiais
df.fillna('', inplace=True)
df.CNPJ = df.CNPJ.str.replace('.', '').str.replace('/', '').str.replace('-', '')
df.CEP = df.CEP.str.replace('-', '')

# Ajusta nome da coluna e troca os tipos de cozinha pelos seus ids
df["Tipo de Cozinha"] = df["Tipo de Cozinha"].replace(dict(map(reversed, tipos_de_cozinha.items())))
df.rename(columns={"Tipo de Cozinha": "Tipo de Cozinha id"}, inplace=True)

# Cria a string das colunas do banco
cols = [slugify(col, separator='_') for col in df.columns]
cols_string = ', '.join(cols)

# Cria a string dos valores dos restaurantes
vals_string = ', '.join(
    df.apply(lambda row: "('" + "', '".join(map(str, row.values)) + "')", axis=1).values
)

# Cria a string final de insert
insert = f"insert into restaurante ({cols_string}) values {vals_string};"
print(insert)
