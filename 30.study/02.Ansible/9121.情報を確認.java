■■■■■■■■■■■■■■■■■■■■■■■■■■ 情報収集
▼ ping を打つ // ansible サーバに対して
ansible ansible -m ping

▼ facts を出力
ansible localhost -m setup

▼ facts の値をタスクで利用
-------------------------------------------------
tasks:
  - name: same task
    value: "{{ ansible_someServerNameInIni.ipv4.address}}"
    // facts として出力された値を、上記のように参照
-------------------------------------------------

▼ タスクの戻り値を見たい場合
-------------------------------------------------
// - tasks:
//   - name: set hostname
    register: set_hostname_result // タスクの戻り値を取得し、
  // - name: Debug registered variable
    debug:
      var: set_hostname_result// デバッグで出力する
-------------------------------------------------
▼ Ansible のドキュメントを見る
ansible-doc
■■■■■■■■■■■■■■■■■■■■■■■■■■ 簡単なコマンド
▼ ファイルを配置する
ansible ansible -m file -a 'path=~/ansible_test.txt state=touch mode 0644'
