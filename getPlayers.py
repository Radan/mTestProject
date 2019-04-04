import requests

team_data_url = 'https://macclesfield.fantasyclubcricket.co.uk/includes/scripts/get_team_data.php'
data = {"teamID": 1, "gameweek": 0}

player_list = []

teams = requests.get('https://macclesfield.fantasyclubcricket.co.uk/includes/teamlist.php')
for team in teams.json():
    data["teamID"] = team['team_id']
    r = requests.post(team_data_url, json=data)
    players = r.json()['team_players']
    for player in players:
        player_list.append(player['name'])

player_set = set(player_list)

sort_list = []


for player in player_set:
    sort_list.append((player_list.count(player), player))

sort_list.sort(reverse=True)

for player in sort_list:
    print player
