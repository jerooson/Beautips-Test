import requests
import os
import firebase_admin
import json
from firebase_admin import auth
# from firebase_admin.auth import UserRecord
from firebase_admin import credentials
from firebase_admin import firestore
'''
Before running this python file:
1. Go to your folder which contain this python file (using terminal)

2. In terminal under the folder type:  export  GOOGLE_APPLICATION_CREDENTIAL= (path for the credential files I upload)
2.1 Test if you have the envoirnmen variable by checking: echo $GOOGLE_APPLICATION_CREDENTIAL

3. export FIREBASE_WEB_API_KEY=AIzaSyA1VPaPqCXvKbUW4-l_5u5iTEGSiR-YyyM
3.1 Test if you have the envoirnmen variable by checking: echo $FIREBASE_WEB_API_KEY, you should see the same thing you just input
'''



class Firebase_auth:

    #Glolabal param
    def __init__(self):
        self.FIREBASE_WEB_API_KEY = "AIzaSyA1VPaPqCXvKbUW4-l_5u5iTEGSiR-YyyM"
        self.PATH_CRED = '/Users/lin/Downloads/beautips-54eb3-firebase-adminsdk-6ndmv-c206687be3.json'
        self.cred = credentials.Certificate(self.PATH_CRED)
        self.initialize_firestore_sucess = False
        self.sign_in_success = False
        self.rest_api_url =f"https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword"

        self.initialize_firebase()



    def initialize_firebase(self):
        try:
            self.app = firebase_admin.initialize_app(self.cred)
            self.initialize_firestore_sucess = True
        except Exception as e:
            print(e)


    def initialize_firestore_retrieve_data(self, stylistName):
        '''
        This method retreive data from firbase
        now it returns nothing
        '''
        if self.initialize_firestore_sucess and self.sign_in_success:
            db = firestore.client()
            users_ref = db.collection(u'Stylists').document(stylistName).get().to_dict()
            print(users_ref)
            return users_ref
            # get all the nodes in the Styist node
            # docs = users_ref.stream()
            # #get all stylists
            # for doc in docs:
            #     print(f'{doc.id} => {doc.to_dict()}')

    def retrieve_image(self, stylistName):
        '''
        This method retreive data from firbase
        now it returns nothing
        '''
        if self.initialize_firestore_sucess and self.sign_in_success:
            db = firestore.client()
            users_ref = db.collection(u'Images').where(u'ownerId', '==', stylistName).get()

            images  = []
            for doc in users_ref:
                images.append(doc.to_dict())
            print(images)
            return images
            # get all the nodes in the Styist node
            # docs = users_ref.stream()
            # #get all stylists
            # for doc in docs:
            #     print(f'{doc.id} => {doc.to_dict()}')


       

    def sign_in_with_email_and_password(self, email, password, return_secure_token = True):
        '''
        Sign in method
        '''
        payload = json.dumps({
            "email": email,
            "password": password,
            "returnSecureToken": return_secure_token ##get token
        })

        login_result = requests.post(self.rest_api_url,
                        params={"key": self.FIREBASE_WEB_API_KEY},
                        data=payload).json()
        try:
            #if there is error
            print(login_result["error"]['message'])
            return False
        except:
            #here can send request to front end  and used shared pref
            print("login sucess")
            self.sign_in_success = True
            print(login_result)
            
            return True
        


if __name__ == "__main__":

    # this email address is in the firebase already
    email = "beautipstestuser@gmail.com"
    password = "123456"
    Firebase_instance = Firebase_auth()
    Firebase_instance.sign_in_with_email_and_password(email, password)
    # Firebase_instance.get_storage_bucket()
    Firebase_instance.retrieve_image("Abby")


