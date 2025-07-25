package factory;

import models.BotDifficultyLevel;
import strategies.botPlayingStrategy.BotPlayingStrategy;
import strategies.botPlayingStrategy.EasyBotPlayingStrategy;
import strategies.botPlayingStrategy.HardBotPlayingStrategy;
import strategies.botPlayingStrategy.MediumBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel botDifficultyLevel) {
        if(botDifficultyLevel == BotDifficultyLevel.EASY) {
            return new EasyBotPlayingStrategy();
        } else if (botDifficultyLevel == BotDifficultyLevel.MEDIUM) {
            return new MediumBotPlayingStrategy();
        } else if (botDifficultyLevel == BotDifficultyLevel.HARD) {
            return new HardBotPlayingStrategy();
        }
        return null;
    }
}
