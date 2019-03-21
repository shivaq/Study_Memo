# Tule は immutable な List

# for ループ
-------------------------------------------------
dimensions = (200, 50)
for dimension in dimensions:
  print(dimension)
-------------------------------------------------

# PIL.Image.size は、(width, height) のtuple として生成される引数
image.thumbnail(tuple(x / 2 for x in image.size))
