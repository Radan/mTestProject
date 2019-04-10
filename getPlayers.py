import requests

team_data_url = 'https://macclesfield.fantasyclubcricket.co.uk/includes/scripts/get_team_data.php'
data = {"teamID": 1, "gameweek": 0}

player_list = []
captain_list = []

teams = requests.get('https://macclesfield.fantasyclubcricket.co.uk/includes/teamlist.php')
for team in teams.json():
    data["teamID"] = team['team_id']
    r = requests.post(team_data_url, json=data)
    players = r.json()['team_players']
    for player in players:
        player_list.append(player['name'])
        if player['is_captain']:
            captain_list.append(player['name'])


sorted_players = []
sorted_captains = []

for captain in set(captain_list):
    sorted_captains.append((captain_list.count(captain), captain))

for player in set(player_list):
    sorted_players.append((player_list.count(player), player))

sorted_players.sort(reverse=True)
sorted_captains.sort(reverse=True)

for player in sorted_players:
    print player

print "------------------------------------------------------------"

for captain in sorted_captains:
    print captain
