import cv2
import time
import os

def show_webcam(sec, dirName):
    face_cascade = cv2.CascadeClassifier('haarcascade_frontalface_alt21.xml')
    timy = 20
    cam = cv2.VideoCapture(0)
    deltaSec = (int(round(time.time()))) - sec
    i = 0
    while(deltaSec < timy):
        deltaSec = (int(round(time.time()))) - sec
        val, img = cam.read()
        cv2.imshow('webcam', img)
        faces = face_cascade.detectMultiScale(img, 1.3, 5)
        title = 'cropped'+str(i)+'.jpg'
        dir = dirName + "/" + title
        for (x, y, w, h) in faces: 
            img = img[y:y+h, x:x+w]
        cv2.imwrite(dir, img)
        i = i+1
        if (cv2.waitKey(1)==27):
            break
    cv2.destroyAllWindows()


    

def main():
    name = input("Insert your name: ")
    dirName = "/Users/simone/Desktop/iscritti/"+name
    os.system("mkdir "+dirName)
    sec = (int(round(time.time())))
    deltaSec = (int(round(time.time()))) - sec
    show_webcam(sec, dirName)

if __name__ == '__main__':
    main()
