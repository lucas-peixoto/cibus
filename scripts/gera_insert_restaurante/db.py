from mysql.connector import connect, Error


def get_tipos_de_cozinha():
    conn = connect(host="localhost", database='cibus',
                   user='root', password='')
    cursor = conn.cursor()
    cursor.execute("select * from tipo_de_cozinha;")
    records = cursor.fetchall()
    cursor.close()
    conn.close()

    return dict(records)
