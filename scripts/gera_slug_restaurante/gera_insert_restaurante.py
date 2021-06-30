from slugify import slugify
from db import Connection
import warnings

warnings.filterwarnings('ignore')

conn = Connection()
restaurantes = conn.get_restaurantes()
conn.close()

for restaurante in restaurantes:
    id = restaurante[0]
    slug = slugify(restaurante[1])
    print(f"update restaurante set slug='{slug}' where id={id};")
