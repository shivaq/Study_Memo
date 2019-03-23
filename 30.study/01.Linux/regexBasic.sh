Match の値のみヒットして、 Skip の値はヒットしない 正規表現検索をする

その他の正規表現
-------------------------------------------------
\D →数値以外
\b
? オプション
-------------------------------------------------

▼ わからんかった群
-------------------------------------------------
# Capture		Jan 1987 と 1987	をそれぞれ。
Jan 1987
# Capture		May 1969 と 1969	をそれぞれ。
May 1969
# Capture		Aug 2011 と 2011	をそれぞれ。
Aug 2011
-------------------------------------------------
# ^(\w+\s(\d+))
# カッコがネストされている




-------------------------------------------------
3.14529
-255.34
128
1.9e10
123,340.00
720p //unmatch
-------------------------------------------------
# ^-?\d+(,\d+)*(\.\d+(e\d+)?)?$













-------------------------------------------------
Match	Ana
Match	Bob
Match	Cpc
Skip	aax
Skip	bby
Skip	ccz
-------------------------------------------------
# [A-C]..


-------------------------------------------------
Match	wazzzzzup
Match	wazzzup
Skip	wazup
-------------------------------------------------
# waz{3,7}up z が 3-7 の範囲で繰り返している


-------------------------------------------------
Match	1 file found?
Match	2 files found?
Match	24 files found?
Skip	No files found.
-------------------------------------------------
# \d+ files? found\?
# /d →数値    ? →0 か 1


-------------------------------------------------
https://regexone.com/lesson/capturing_groups?
	# キャプチャ →file_record_transcript
file_record_transcript.pdf
# キャプチャ →file_07241999
file_07241999.pdf
#Skip
testfile_fake.pdf.tmp
-------------------------------------------------
# ^(file.*)\.pdf$
# () 内の値をグループとして抽出して使えるらしい。




-------------------------------------------------
Match	I love cats
Match	I love dogs
Skip	I love logs
Skip	I love cogs
-------------------------------------------------
# I love (cats|dogs)

-------------------------------------------------
Match	The quick brown fox jumps over the lazy dog.
Match	There were 614 instances of students getting 90.0% or above.
Match	The FCC had to censor the network for saying &$#*@!.
-------------------------------------------------
# The.+\.$








# -------------------------------------------------
Match	cat.
Match	896.
Match	?=+.
Skip	abc1
-------------------------------------------------
# ...\.


-------------------------------------------------
Match	can
Match	man
Match	fan
Skip	dan
Skip	ran
Skip	pan
-------------------------------------------------
# [c,m,f]an
# [^drp]an


-------------------------------------------------
Match	hog
Match	dog
Skip	bog
-------------------------------------------------
# [^b]og





-------------------------------------------------
Match	aaaabcc
Match	aabbbbc
Match	aacc
Skip	a
-------------------------------------------------
# aa+b*c+
# + →一回以上  * →0回以上の繰り返し



-------------------------------------------------
Match	1.   abc	Success
Match	2.	abc	Success
Match	3.           abc	Success
Skip	4.abc
-------------------------------------------------
# \d\.\s+abc\s+Success



-------------------------------------------------
Mission: successful
#Skip
Last Mission: unsuccessful
#Skip
Next Mission: successful upon capture of target
-------------------------------------------------
# ^Mission: successful
# Mission: successful$









-------------------------------------------------
# Capture	1280 と 720 をそれぞれ
1280x720
# Capture		1920 と 1600 をそれぞれ
1920x1600
# Capture	1024 と 768 をそれぞれ
1024x768
-------------------------------------------------
# ^(\d+)x(\d+)







-------------------------------------------------
https://regexone.com/problem/matching_phone_numbers?
-------------------------------------------------