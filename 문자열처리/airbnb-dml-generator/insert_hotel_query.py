from random import randrange, randint

class GlobalVar:
    def __init__(self):
        self.detail_id = 0

    def get_detail_id(self):
        self.detail_id += 1
        return self.detail_id

def price_gen():
    return randrange(10000, 1000000, 10000)

def rate_gen():
    return randrange(5)

def random_location():
    lat = randrange(37480, 37500)
    long = randrange(12702, 12706)
    return round(lat * 0.001, 3), round(long * 0.01, 2)

def occupancy_gen():
    return randrange(1, 10)

def yes_or_no():
    return 'true' if randint(0, 1) == 1 else 'false'

def location_gen():
    lat, long = random_location()
    return 'POINT({},{})'.format(lat, long)

def random_img():
    idx = randint(0, len(img_urls) - 1)
    return img_urls[idx]

def create_insert():
    f_hotels = open('insert_hotel_query.txt', 'w+')
    f_details = open('insert_hotel_detail_query.txt', 'w+')
    for id, host in enumerate(open('names.txt'), start=1):
        f_hotels.write("INSERT INTO hotel " +
                       "(hotel_id, title, price, `host`, occupancy, location, rate, has_kitchen, has_wifi, has_aircon, has_hairdryer, has_tv, main_img) " +
                       "VALUES ({}, '{}', {}, '{}', {}, {}, {}, {}, {}, {}, {}, {}, '{}');\n".format(id, "hotel number " + str(id), price_gen(), host.rstrip(), occupancy_gen(), location_gen(), rate_gen(), yes_or_no(), yes_or_no(), yes_or_no(), yes_or_no(),  yes_or_no(), random_img()))

        for i in range(randint(1,5)):
            detail_id = variable.get_detail_id()
            f_details.write("INSERT INTO detail_image " +
                            "(id, image_url, hotel_id) " +
                            "VALUES ({}, '{}', {});\n".format(detail_id, random_img(), id))
    f_hotels.close()
    f_details.close()

f_imgs = open('hotel_imgs.txt', 'r')
img_urls = f_imgs.read().splitlines()
variable = GlobalVar()
create_insert()
