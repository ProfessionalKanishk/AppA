import mysql.connector




cnx = mysql.connector.connect(user='root',password='s1101419',host='127.0.0.1',database='test_server')

cursor = cnx.cursor()

event = "Fun Test, plz work"
food = "protein"
location = "at the gym"
time = "2:00pm"

cursor.execute("INSERT INTO food_table VALUES (%s, %s, %s, %s)", (event,food,location,time))
increment = cursor.lastrowid # not sure if this does much, but was recommended to make data neater

# Make sure data is committed to the database
cnx.commit()

cursor.close()

cnx.close()