
▼ リスト内に、該当する値がないかどうかチェック
-------------------------------------------------
# からのスペースがないかどうかチェック
def is_every_spaces_marked(board):
    return ' ' not in board[1:]
-------------------------------------------------


# List 内要素チェック
-------------------------------------------------
# 含んでいるか
requested_toppings = ['mushrooms', 'onions', 'pineapple']
    'mushrooms' in requested_toppings
-------------------------------------------------
# 含まれていないか？     not を使う
banned_users = ['andrew', 'carolina', 'david']
user = 'marie'

if user not in banned_users:
    print(user.title() + ", you can post a response if you wish.")
-------------------------------------------------
# List が空かどうかチェック

requested_toppings = []
if requested_toppings:
    for requested_topping in requested_toppings:
        print("Adding " + requested_topping + ".")
        print("\nFinished making your pizza!")
            else:
        print("Are you sure you want a plain pizza?")
-------------------------------------------------
