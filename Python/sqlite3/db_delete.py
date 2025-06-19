#!/usr/bin/python

import sqlite3

conn = sqlite3.connect('db/test.db')
c = conn.cursor()
print ("数据库打开成功")

c.execute("DELETE from COMPANY where ID = 2;")
conn.commit()
print(f"Total number of rows updated : {conn.total_changes}")

cursor = conn.execute("SELECT id, name, address, salary  from COMPANY")
for row in cursor:
   print(f"ID = {row[0]}")
   print(f"NAME = {row[1]}")
   print(f"ADDRESS = {row[2]}")
   print(f"SALARY =  {row[3]} \n")

print ("数据Delete成功")
conn.close()