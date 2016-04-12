import core.ApplicationData
import core.net.HttpConnectionSession
import database.TestDatabase
import game.TosTextGame
import game.data.virtualDataBase.ClassDataParser
import game.data.virtualDataBase.DataLoader
import game.data.virtualDataBase.VirtualDataBase
import threading.SessionManager

def language = "default";

new TestDatabase();

def dataBase = new VirtualDataBase();
def classLoader = new DataLoader("classes", new ClassDataParser(language), dataBase)

classLoader.execute();



TosTextGame tosTextGame = new TosTextGame(dataBase);

def sessionManager = new SessionManager();
def applicationData = new ApplicationData();

sessionManager.addSession(new HttpConnectionSession(applicationData, tosTextGame))