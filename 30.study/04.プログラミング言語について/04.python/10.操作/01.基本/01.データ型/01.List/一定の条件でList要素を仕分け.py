



▼ 処理前リストから、処理完了リストに仕分け関数
# 関数にリストを渡し、リストが変更される
-------------------------------------------------
def print_models(unprinted_designs, completed_models):
    """
    リストから各要素がなくなるまで出力。
    出力された要素は、完了リストに格納。
    """
    while unprinted_designs:
        current_design = unprinted_designs.pop()
        # Simulate creating a 3D print from the design.
        print("Printing model: " + current_design)
        completed_models.append(current_design)

def show_completed_models(completed_models):
    """出力された全モデルを表示."""
    print("\nThe following models have been printed:")

    for completed_model in completed_models:
        print(completed_model)

completed_models = []
unprinted_designs = ['iphone case', 'robot pendant', 'dodecahedron']

print_models(unprinted_designs, completed_models)
show_completed_models(completed_models)
-------------------------------------------------
