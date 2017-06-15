from urllib.request import urlopen as uReq
from bs4 import BeautifulSoup as soup

my_url = 'https://www.newegg.ca/Product/ProductList.aspx?Submit=ENE&IsNodeId=1&N=100007708%20600536049%20600536050%20600565061%20600565504%20600565674%20601107975%20601203793%20601204369%20601210955%20601205646%20601202919%20601203927%20601203901%20601294835%20601295933%20601194948%20601296707&cm_sp=Cat_video-Cards_1-_-Visnav-_-Gaming-Video-Cards_1'

#opening up connecti, grapbbin page
uClient = uReq(my_url)
page_html = uClient.read()
uClient.close()

#html parshing
page_soup = soup(page_html, "html.parser")

#grab each product
containers = page_soup.findAll("div",{"class":"item-container"})

for container in containers:
	brand = container.div.div.a.img["title"]
	
	title_container = container.findAll("a",{"class":"item-title"})
	product_name = title_container[0].text
	
	shipping_container = container.findAll("li",{"class":"price-ship"})
	shipping = shipping_container[0].text.strip()
	
	print ("brand: "+ brand)
	print ("product name: " + product_name)
	print ("shipping: " + shipping)