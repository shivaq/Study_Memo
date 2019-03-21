#!C:\Users\Yasuaki\Dropbox\01.study\Network\Python\udemyPython\Python_Basic\Projects\Lambda\StopEc2\venv\Scripts\python.exe
# EASY-INSTALL-ENTRY-SCRIPT: 'pip==9.0.1','console_scripts','pip'
__requires__ = 'pip==9.0.1'
import re
import sys
from pkg_resources import load_entry_point

if __name__ == '__main__':
    sys.argv[0] = re.sub(r'(-script\.pyw?|\.exe)?$', '', sys.argv[0])
    sys.exit(
        load_entry_point('pip==9.0.1', 'console_scripts', 'pip')()
    )
