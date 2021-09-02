package bot.processor;

import org.openqa.selenium.WebElement;

import java.util.List;

public class BotValidationService {

    public BotValidationService() {
    }

    public boolean assertExistingStatuses(List<WebElement> statusPic,List<WebElement> statusText){

        return statusPic.size() != 0 || statusText.size() != 0;
    }

    public boolean assertEqualNames(String currentStatus,String lastStatus){

        return currentStatus.trim().equals(lastStatus.trim());
    }

    public boolean assertFirstStatusIsTextOrPicStatus(String fullStatus,String status){
        return fullStatus.contains(status);
    }



}
