package nl.tudelft.jpacman.unitTest;

import com.google.common.collect.Lists;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.npc.ghost.Clyde;
import nl.tudelft.jpacman.npc.ghost.GhostFactory;
import nl.tudelft.jpacman.npc.ghost.GhostMapParser;
import nl.tudelft.jpacman.npc.ghost.Navigation;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

public class ClydeTest {
    private PlayerFactory playerfactory;
    private LevelFactory levelfactory;
    private GhostFactory ghostFactory;
    private BoardFactory boardfactory;
    private GhostMapParser ghostMapParser;
    private Level level;


    /**
     * 初始化
     * **/

    @BeforeEach
    void setup(){
        PacManSprites sprites = new PacManSprites();
        playerfactory = new PlayerFactory(sprites);
        ghostFactory = new GhostFactory(sprites);
        levelfactory = new LevelFactory(sprites, ghostFactory,mock(PointCalculator.class));
        boardfactory = new BoardFactory(sprites);
        ghostMapParser = new GhostMapParser(levelfactory, boardfactory, ghostFactory);
    }

    /**
     * 这个测试玩家远离Clyde时的情况（10个方块单位）
     * **/
    @Test
    void testClydeFarFromPlayer(){
        level = ghostMapParser.parseMap(
            Lists.newArrayList("##############", "#P          C#", "##############"));
        Player p = playerfactory.createPacMan();
        level.registerPlayer(p);
        p.setDirection(Direction.WEST);
        Clyde c = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assertThat(c.nextAiMove()).isEqualTo(Optional.of(Direction.WEST));


    }

    /**
     *
     * 这个测试玩家靠近Clyde时的情况（5个方块单位）
     * **/
    @Test
    void testClydeNearPlayer(){
        level = ghostMapParser.parseMap(
            Lists.newArrayList("############", "#P     C   #", "############"));
        Player p = playerfactory.createPacMan();
        level.registerPlayer(p);
        p.setDirection(Direction.WEST);
        Clyde c = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assertThat(c.nextAiMove()).isEqualTo(Optional.of(Direction.EAST));
    }


    /**
     *
     * 测试玩家为空的时候的情况
     * **/
    @Test
    void testPlayerIsNull(){
        level = ghostMapParser.parseMap(
            Lists.newArrayList("#############", "#          C#", "#############"));
        Clyde c = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assertThat(c.nextAiMove()).isEqualTo(Optional.empty());

    }


    /**
     *
     * 测试Clyde无法接近玩家时的情况（Path为空）
     * **/
    @Test
    void testClydeCantNear(){
        level = ghostMapParser.parseMap(
            Lists.newArrayList("############", "#P    #C   #", "############"));
        Player p = playerfactory.createPacMan();
        level.registerPlayer(p);
        p.setDirection(Direction.WEST);
        Clyde c = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assertThat(c.nextAiMove()).isEqualTo(Optional.empty());

    }



}
