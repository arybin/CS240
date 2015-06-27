from PIL import Image
from PIL import ImageFilter
import os 
basedir = os.path.dirname(__file__)
print('whatever'+basedir+'whatever')
im = Image.open(os.path.join(basedir, "Photos/Deadpool.jpg"))
Deadpoolflip = im.rotate(90)
Deadpoolflip.save(os.path.join(basedir, "Photos/Deadpoolflip.jpg"))

# Now I will Make the Image Gray.

Gray = im.convert("L")
Gray.save(os.path.join(basedir, "Photos/Deadpoolgray.jpg"))

# Now for something Diffrent

Blur = im.filter(ImageFilter.BLUR)
Blur.save(os.path.join(basedir, "Photos/DeadpoolBlur.jpg"))
