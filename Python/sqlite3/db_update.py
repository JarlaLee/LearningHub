#!/usr/bin/python
"""
    如果想要更新数据库中的记录
"""
import sqlite3

conn = sqlite3.connect('db/test.db')
c = conn.cursor()
print ("数据库打开成功")
cursor = conn.execute("SELECT id, name, address, salary  from COMPANY where ID=1")
for row in cursor:
   print(f"ID = {row[0]}")
   print(f"NAME = {row[1]}")
   print(f"ADDRESS = {row[2]}")
   print(f"SALARY =  {row[3]} \n")

c.execute("UPDATE COMPANY set SALARY = 35000.00 where ID=1")
conn.commit()

print(f"Total number of rows updated : {conn.total_changes}")

cursor = conn.execute("SELECT id, name, address, salary  from COMPANY where ID=1")
for row in cursor:
   print(f"ID = {row[0]}")
   print(f"NAME = {row[1]}")
   print(f"ADDRESS = {row[2]}")
   print(f"SALARY =  {row[3]} \n")

print ("数据update成功")
conn.close()