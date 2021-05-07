import random

f_game = open('insert_game_query.txt', 'w+')
team_num_list = [i for i in range(1, 31)]
for i in range(30):
    n1, n2 = random.randint(0, 29), random.randint(0, 29)
    team_num_list[n1], team_num_list[n2] = team_num_list[n2], team_num_list[n1]

for id, i in enumerate(range(0, 30, 2), start=1):
    game_match = team_num_list[i:i+2]
    f_game.write(
        "INSERT INTO game(id, home_id, away_id) VALUES ({}, {}, {});\n".format(
            id, game_match[0], game_match[1]))
