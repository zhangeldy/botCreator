package util;

import util.accesslevel.AccessLevel;
import util.database.DataRec;

public class GlobalParam {

    private String inputText;
    private DataRec queryData;
    private AccessLevel accessLevel;
    private long chatId = -1;

    public GlobalParam() {
        this.queryData = new DataRec();
        this.accessLevel = AccessLevel.READ;
        this.chatId = -1;
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public DataRec getQueryData() {
        return queryData;
    }

    public void setQueryData(DataRec queryData) {
        this.queryData = queryData;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

}
