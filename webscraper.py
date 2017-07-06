#clear table

import pymysql
pymysql.install_as_MySQLdb()
import MySQLdb

db= MySQLdb.connect(user='root',password='s1101419',host='159.203.127.147',database='food_test',port='3306')

cursor= db.cursor()

cursor.execute("TRUNCATE TABLE free_fud")

db.close()

#update table

from urllib.request import urlopen as uReq
from bs4 import BeautifulSoup as soup
import mysql.connector




my_url = 'https://calendar.utexas.edu/calendar/day/2016/8/30'

#opening up connecti, grapbbin page
uClient = uReq(my_url)
page_html = uClient.read()
uClient.close()
cnx = mysql.connector.connect(user='root',password='s1101419',host='159.203.127.147',database='food_test',port='3306')

cursor = cnx.cursor()

#html parshing
page_soup = soup(page_html, "html.parser")

#grab each product
containers = page_soup.findAll("div",{"class":"item event_item vevent"})

for container in containers:

	event_description = container.findAll("h4",{"class":"description"})
	event = event_description[0].text.strip()
	str1 = "free food"
	str2 = "free pizza"
	lines = event.split('\n')
	
	
	if any(str1 in s for s in lines):
		#print("***********************")
		event_title = container.findAll("h3",{"class":"summary"})
		event_name = event_title[0].text.strip()
	
		time_html = container.findAll("div",{"class":"dateright"})
		time = time_html[0].text.strip()
		
		location_html = container.findAll("div",{"class":"location"})
		location = location_html[0].text.strip()
		
		event_title = str(event_title)
		
		food = "N/A"
		print ("event name: "+ event_name)
		print ("food: "+food)
		print ("location: "  + location)
		print ("time: " + time)
		
		cursor.execute("INSERT INTO free_fud VALUES (%s, %s, %s, %s)", (event_name,food,location,time))
		increment = cursor.lastrowid # not sure if this does much, but was recommended to make data neater
	
		print("********************")
	elif any(str2 in s for s in lines):
		#print("*************************")
		event_title = container.findAll("h3",{"class":"summary"})
		event_name = event_title[0].text.strip()
	
		time_html = container.findAll("div",{"class":"dateright"})
		time = time_html[0].text.strip()
		
		location_html = container.findAll("div",{"class":"location"})
		location = location_html[0].text.strip()
		
		event_title = str(event_title)
		
		food = "pizza"
		print ("event name: "+ event_name)
		print ("food: "+ food)
		print ("location: "  + location)
		print ("time: " + time)
		
		cursor.execute("INSERT INTO free_fud VALUES (%s, %s, %s, %s)", (event_name,food,location,time))
		increment = cursor.lastrowid # not sure if this does much, but was recommended to make data neater
	
		print("********************")
	
	else:
	#do nothing
		g=0

		
cnx.commit()

cursor.close()

cnx.close()