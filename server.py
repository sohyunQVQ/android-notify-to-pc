from flask import Flask
from flask import request

import os
import platform

app = Flask(__name__)

@app.route('/', methods=['POST', 'GET'])
def index():
    if request.method == 'POST':
        data = request.get_json()
        content = data["content"]
        sender = data['sender']

        platformos = platform.system()
        if platformos == 'Darwin':
            os.system("osascript -e 'display notification \"%s\" with title \"收到来自：%s的通知\"'" % (content, sender))
        elif platformos == 'Windows':
            from win10toast import ToastNotifier
            toaster = ToastNotifier()
            toaster.show_toast(u'收到来自：%s的通知' % sender, u'%s' % content)

    return "hellowrold"

if __name__ == "__main__":

    app.run(host='192.168.1.220')

