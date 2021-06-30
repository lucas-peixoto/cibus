from mysql.connector import connect


class Connection:
    def __init__(self):
        self.conn = connect(host="localhost", database='cibus', user='root', password='')
        self.cursor = self.conn.cursor()

    def get_restaurantes(self):
        self.cursor.execute("select * from restaurante;")
        return self.cursor.fetchall()

    def close(self):
        self.cursor.close()
        self.conn.close()
