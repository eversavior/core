package core.net;

import core.ApplicationData;
import core.GlobalSettings;
import core.net.http.HttpDataListener;
import core.net.http.HttpRequestData;
import core.net.http.html.HtmlInfoPrinter;
import core.net.http.html.HtmlInfoWriter;
import core.net.http.html.HttpInfoOutput;
import core.net.http.html.actions.ActionHooks;
import core.net.http.html.actions.ActionRestart;
import core.net.http.html.popups.PopupHooks;
import core.net.http.html.popups.RestartPopupWriter;
import core.net.http.html.writers.*;
import game.TosTextGame;
import game.http.*;
import lombok.extern.java.Log;
import threading.Session;
import threading.SessionManager;
import core.net.http.HttpServer;

import java.util.HashMap;

@Log
public class HttpConnectionSession extends Session implements HttpDataListener
{
    private static final int HTTP_HANDLER_SESSION = -100;
    private HttpServer httpServer;
    private SessionManager sessionManager;

    private HttpInfoOutput httpInfoOutput;
    private HtmlInfoPrinter htmlInfoPrinter;

    private HashMap<String, HtmlInfoWriter> writesMap = new HashMap<String, HtmlInfoWriter>();

    private ApplicationData applicationData;

    private TosTextGame tosTextGame;

    public HttpConnectionSession(ApplicationData applicationData, TosTextGame tosTextGame)
    {
        super("HttpConnectionSession", HTTP_HANDLER_SESSION);
        this.applicationData = applicationData;

        this.tosTextGame = tosTextGame;

        httpServer = new HttpServer(GlobalSettings.webPort);
        httpServer.setListener(this);

        httpInfoOutput = new HttpInfoOutput();
        httpInfoOutput.setHttpServer(httpServer);
        htmlInfoPrinter = new HtmlInfoPrinter();
        htmlInfoPrinter.setOutput(httpInfoOutput);

        initialize();
    }

    private void createInfoHook(HtmlInfoWriter htmlInfoWriter, String hook)
    {
        writesMap.put(hook, htmlInfoWriter);
        htmlInfoWriter.setPrinter(htmlInfoPrinter);
    }

    public void initialize()
    {
        MainInfoWriter mainInfoWriter = new MainInfoWriter(sessionManager, applicationData);

        createInfoHook(mainInfoWriter, InfoHooks.SERVER);


        TOSBaseItemInfo tosBaseItemInfo = new TOSBaseItemInfo();

        createInfoHook(tosBaseItemInfo, TOSBaseActions.ITEM_INFO);

        RestartPopupWriter restartPopupWriter = new RestartPopupWriter();

        createInfoHook(restartPopupWriter, PopupHooks.RESTART);


        ActionRestart actionRestart = new ActionRestart(sessionManager);

        createInfoHook(actionRestart, ActionHooks.RESTART);

        createGameHooks();
    }

    private void createGameHooks()
    {
        CreateCharacterHook craeteCharacterHook = new CreateCharacterHook(tosTextGame);
        createInfoHook(craeteCharacterHook, TosGameHooks.ACTION_CREATE_CHARACTER);

        CharactersListHook charactersListHook = new CharactersListHook(tosTextGame);
        createInfoHook(charactersListHook, TosGameHooks.ACTION_LIST_CHARACTERS);

        FightHook fightHook = new FightHook(tosTextGame);
        createInfoHook(fightHook, TosGameHooks.ACTION_FIGHT);

        CharacterInfoHook characterInfoHook = new CharacterInfoHook(tosTextGame);
        createInfoHook(characterInfoHook, TosGameHooks.ACTION_INFO_CHARACTER);
    }

    @Override
    public void afterRun() {
        super.afterRun();

        httpServer.listen();
    }

    @Override
    public void handleHttpRequest(HttpServer httpServer, HttpRequestData httpRequestData)
    {
        if(httpRequestData == null)
        {
            log.info("http request empty ");
            return;
        }
        else //if(httpRequestData.path.length() > 9)
        {
            String path = httpRequestData.path;
            /*String sig = path.substring(0, 9);

            if(!sig.equals("QW9357701"))
            {
                //htmlInfoPrinter.html();
                htmlInfoPrinter.print("requested data not found");
                //htmlInfoPrinter.br();
                //htmlInfoPrinter.htmlEnd();
            }
            else*/
            //{
                //path = path.substring(10);

                HtmlInfoWriter currentWriter = writesMap.get(path);

                if (currentWriter != null) {
                    //htmlInfoPrinter.doctype();
                    //htmlInfoPrinter.html();

                    //htmlInfoPrinter.head();
                    //htmlInfoPrinter.print("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\"/>");
                    //htmlInfoPrinter.headEnd();
                    //htmlInfoPrinter.body();
                    currentWriter.writeInfo(httpRequestData);
                    //htmlInfoPrinter.bodyEnd();
                    //htmlInfoPrinter.htmlEnd();
                } else {
                    //htmlInfoPrinter.html();
                    htmlInfoPrinter.print("requested data not found");
                    //htmlInfoPrinter.br();
                    //htmlInfoPrinter.htmlEnd();
                }
            //}
        }

        //GenerateInfoWriter generateInfoWriter = new GenerateInfoWriter(httpRequestData);
        //generateInfoWriter.setPrinter(htmlInfoPrinter);
        //generateInfoWriter.writeInfo(httpRequestData);
    }
}
