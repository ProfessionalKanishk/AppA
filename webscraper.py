from urllib.request import urlopen as uReq
from bs4 import BeautifulSoup as soup
import mysql.connector


my_url = 'https://calendar.utexas.edu/calendar/day/2016/8/30'

#opening up connecti, grapbbin page
uClient = uReq(my_url)
page_html = uClient.read()
uClient.close()
cnx = mysql.connector.connect(user='root',password='s1101419',host='127.0.0.1',database='test_server')

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
	
	print ('|||||'.join(lines))
	
	if any(str1 in s for s in lines):
		print("***********************")
		event_title = container.findAll("h3",{"class":"summary"})
		event_name = event_title.find('a').text
	
		time_html = container.findAll("div",{"class":"dateright"})
		time = time_html[0].text.strip()
		
		location_html = container.findAll("div",{"class":"location"})
		location = location_html[0].text.strip()
		
		event_title = str(event_title)
		print ("event name: "+ event_title)
		print ("food: food?")
		print ("location: "  + location)
		print ("time: " + time)
		
		#cursor.execute("INSERT INTO food_table VALUES (%s, %s, %s, %s)", (event,food,location,time))
		#increment = cursor.lastrowid # not sure if this does much, but was recommended to make data neater
	
		cnx.commit()
	elif any(str2 in s for s in lines):
		print("*************************")
		event_title = container.findAll("h3",{"class":"summary"})
		event_name = event_title[0].text.strip()
	
		time_html = container.findAll("div",{"class":"dateright"})
		time = time_html[0].text.strip()
		
		location_html = container.findAll("div",{"class":"location"})
		location = location_html[0].text.strip()
		
		event_title = str(event_title)
		print ("event name: "+ event_title)
		print ("food: pizza")
		print ("location: "  + location)
		print ("time: " + time)
		
		#cursor.execute("INSERT INTO food_table VALUES (%s, %s, %s, %s)", (event,food,location,time))
		#increment = cursor.lastrowid # not sure if this does much, but was recommended to make data neater
	
		cnx.commit()

	
	else:
	#do nothing
		g=0

cnx.commit()

cursor.close()

cnx.close()