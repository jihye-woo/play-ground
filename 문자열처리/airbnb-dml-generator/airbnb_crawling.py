from bs4 import BeautifulSoup
import requests

headers = {
    'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36'}
url_prefix = 'https://www.airbnb.com/s/'
url_surfix = '/homes?tab_id=home_tab&refinement_paths%5B%5D=%2Fhomes&flexible_trip_dates%5B%5D=july&flexible_trip_dates%5B%5D=june&flexible_trip_lengths%5B%5D=weekend_trip&date_picker_type=calendar&disable_auto_translation=false&source=structured_search_input_header&search_type=search_query'


f_imgs = open('hotel_imgs.txt', 'w+')
for location in open('locations.txt'):
    url = url_prefix + location.rstrip() + url_surfix
    response = requests.get(url, headers=headers)

    # 전체 html 소스
    soup = BeautifulSoup(response.content, 'html.parser')

    # 클래스 이름이 _fhph4u인 div만 받아오기
    table = soup.find('div', {'class': '_fhph4u'})

    # 그 중 img 태그에 있는 정보 받아오기
    imgs = table.find_all('img')

    # img 태그에서 src에 해당하는 데이터 받아오기
    for idx, img in enumerate(imgs):
        source = img.get('src')
        f_imgs.write(source + "\n")

f_imgs.close()

