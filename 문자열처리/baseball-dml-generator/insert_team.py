class GlobalVar:
    def __init__(self):
        self.total_player = 0

    def ge_player_num(self):
        self.total_player += 1
        return self.total_player


f_team = open('insert_team_query.txt', 'w+')
f_player = open('insert_player_query.txt', 'w+')

vars = GlobalVar()

# 팀 명 출처 - https://textlists.info/sports/list-of-mlb-teams/
for team_id, team_name in enumerate(open('team_name.txt'), start=1):
    f_team.write("INSERT INTO team(id, name) VALUES ({}, '{}');\n".format(team_id, team_name.rstrip()));
    for num in range(1, 11):
        f_player.write(
            "INSERT INTO player(id, player_name, player_number, is_pitcher, team_id) VALUES ({}, '{}', {}, {}, {});\n".format(
                vars.ge_player_num(), team_name.rstrip() + ' player ' + str(num), num, 0, team_id))

    # 마지막 주자는 pitcher
    f_player.write(
        "INSERT INTO player(id, player_name, player_number, is_pitcher, team_id) VALUES ({}, '{}', {}, {}, {});\n".format(
            vars.ge_player_num(), team_name.rstrip() + ' player 11', 11, 1, team_id))

f_team.close()
f_player.close()




