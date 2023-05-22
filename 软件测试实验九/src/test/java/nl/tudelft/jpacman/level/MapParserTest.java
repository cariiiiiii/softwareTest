package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.PacmanConfigurationException;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MapParserTest {
    private MapParser mapParser;
    private final LevelFactory levelCreator = mock(LevelFactory.class);
    private final BoardFactory boardFactory = mock(BoardFactory.class);

    @BeforeEach
    void setup() {
        mapParser = new MapParser(levelCreator, boardFactory);
        when(boardFactory.createGround()).thenReturn(mock(Square.class));
        when(boardFactory.createWall()).thenReturn(mock(Square.class));
        when(levelCreator.createGhost()).thenReturn(mock(Ghost.class));
        when(levelCreator.createPellet()).thenReturn(mock(Pellet.class));
    }

    @Test
    @DisplayName("不存在文件名")
    void nullFile() {
        assertThrows(NullPointerException.class, () -> {
            mapParser.parseMap((String) null);
        });
    }

    @Test
    @DisplayName("读取不存在的文件")
    void nonExistFile() {
        String file = "/unreal.txt";
        String message = "找不到该文件: " + file;
        assertThrows(PacmanConfigurationException.class, () -> {
            mapParser.parseMap(file);
        }, message);
    }

    @Test
    @DisplayName("读取存在的文件")
    void existFile() throws IOException {
        String file = "/simplemap.txt";
        assertEquals(null, mapParser.parseMap(file));
    }

    @Test
    @DisplayName("读取无法识别的charmap")
    void unrecongnizedCharMap() throws IOException {
        String file = "/unrecognizedcharmap.txt";
        assertThrows(PacmanConfigurationException.class, () -> {
            mapParser.parseMap(file);
        });
    }

    @Test
    @DisplayName("读取空文件")
    void empty() throws IOException {
        String file = "/empty.txt";
        assertThrows(PacmanConfigurationException.class, () -> {
            mapParser.parseMap(file);
        }, "输入文件必须至少存在一行");
    }

}
