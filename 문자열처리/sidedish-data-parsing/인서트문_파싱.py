import json

global dish_list, img_idx, event_idx, delivery_idx

class Index:
    def __init__(self):
        # 트리가 작성한 쿼리 다음에 추가해주어야 하기 때문에 idx를 클래스에서 따로 관리
        # 만약 처음부터 끝까지 내가 만든다면 초기 idx 값은 모두 0으로 처리해주면 된다.
        self.img_idx = 315
        self.event_idx = 25
        self.delivery_idx = 40

class Dish:
    def __init__(self, dish_dict, section_name):
        self.hash = dish_dict['hash']
        self.top_image = dish_dict['data']['top_image']
        self.thumb_image_list = dish_dict['data']['thumb_images']
        self.delivery_info = dish_dict['data']['delivery_info']
        self.delivery_fee = dish_dict['data']['delivery_fee']
        self.prices = dish_dict['data']['prices']
        self.detail_section = dish_dict['data']['detail_section']
        self.title = ''
        self.delivery_type = []
        self.description = ''
        self.s_price = 0
        self.n_price = 0
        self.badge = []
        self.section_name = section_name

    def update(self, basic_dish_info):
        self.title = basic_dish_info['title']
        self.delivery_type = basic_dish_info['delivery_type']
        self.description = basic_dish_info['description']
        self.s_price = basic_dish_info['s_price'].replace('원', '').replace(',', '')
        self.n_price = basic_dish_info['n_price'].replace('원', '').replace(',', '') if 'n_price' in basic_dish_info else 0
        self.badge = basic_dish_info['badge']


def detail_dish(detail_json_data, section_name):
    dish_dict = json.loads(detail_json_data)
    return Dish(dish_dict, section_name)

def update_dish(basic_json_data):
    dish_dict = json.loads(basic_json_data)
    hash = dish_dict['detail_hash']
    dish_list[hash].update(dish_dict) # 반드시 dish_list에 해당하는 hash 값이 있어야 함.


def insert_dish_query(f, dish):
    f.write('INSERT INTO dish(hash, top_image, title, description, normal_price, special_price, section_name, stock) VALUES ')
    f.write("( '{0.hash}', '{0.top_image}', '{0.title}', '{0.description}', {0.n_price}, {0.s_price}, '{0.section_name}', 100 );\n".format(dish))
    f.write('\n')

def insert_image_query(f, dish, idx):
    for thumb_image in dish.thumb_image_list:
        f.write('INSERT INTO image(id, image_url, dish_hash, is_thumb) VALUES (' + str(idx.img_idx))
        f.write(", '{}', '{}', {});\n".format(thumb_image, dish.hash, 1))
        idx.img_idx += 1
    for detail_image in dish.detail_section:
        f.write('INSERT INTO image(id, image_url, dish_hash, is_thumb) VALUES (' + str(idx.img_idx))
        f.write(", '{}', '{}', {});\n".format(detail_image, dish.hash, 0))
        idx.img_idx += 1
    f.write('\n')

def insert_event_query(f, dish, idx):
    for event in dish.badge:
        f.write('INSERT INTO event(id, badge, dish_hash) VALUES (' + str(idx.event_idx))
        f.write(", '{}', '{}');".format(event, dish.hash))
        idx.event_idx += 1
    f.write('\n')

def insert_delivery_query(f, dish, idx):
    f.write('INSERT INTO delivery(id, is_monday, dish_hash) VALUES (' + str(idx.delivery_idx))
    f.write(", {}, '{}');".format(is_monday(dish.delivery_info), dish.hash))
    idx.delivery_idx += 1
    f.write('\n')

def is_monday(delivery_info):
    return 1 if '월' in delivery_info else 0

def create_dish(single_dish, section_name):
    if single_dish.strip().startswith('{'): # null pointer 방지
        dish = detail_dish(single_dish, section_name)
        if dish.hash not in dish_list:
            dish_list[dish.hash] = dish
        else:
            dish_list[dish.hash].section_name += ',' + section_name



dish_list = {}
idx = Index()

for single_dish in open('soup_basic_json.txt'):
    create_dish(single_dish, 'soup')

for single_dish in open('soup_detail_json.txt'):
    if single_dish.strip().startswith('{'): # null pointer 방지
        update_dish(single_dish)

for single_dish in open('side_basic_json.txt'):
    create_dish(single_dish, 'side')

for single_dish in open('side_detail_json.txt'):
    if single_dish.strip().startswith('{'): # null pointer 방지
        update_dish(single_dish)

f = open('insert_query.txt', 'w+')
for hash, dish in dish_list.items():
    f.write('\n')
    f.write('-- {}\n'.format(hash))
    insert_dish_query(f, dish)
    insert_image_query(f, dish, idx)
    insert_event_query(f, dish, idx)
    insert_delivery_query(f, dish, idx)

f.close()