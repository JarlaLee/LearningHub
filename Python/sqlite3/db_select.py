"""
    数据库已经创建, 然后已经插入样本数据
"""

import sqlite3

conn = sqlite3.connect('db/test.db')
c = conn.cursor()
print("数据库成功打开")

cursor = c.execute("SELECT * FROM COMPANY")
for row in cursor:
    print(f"ID = {row[0]}")
    print(f"NAME = {row[1]}")
    print(f"AGE = {row[2]}")
    print(f"ADDRESS = {row[3]}")
    print(f"SALARY ={row[4]} \n")

print("数据操作成功")
conn.close()