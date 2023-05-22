package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class PlayerCollisionsTest {

    private PlayerCollisions collisions;
    private PointCalculator calculator;

    @Before
    public void setUp() {
        calculator = mock(PointCalculator.class);
        collisions = new PlayerCollisions(calculator);
    }

    @DisplayName("玩家碰到鬼")
    @Order(1)
    @Test
    public void testPlayerCollidingWithGhost() {
        Player player = mock(Player.class);
        Ghost ghost = mock(Ghost.class);

        Mockito.when(player.isAlive()).thenReturn(true);
        Mockito.doNothing().when(player).setAlive(false);

        collisions.collide(player, ghost);

        Mockito.verify(calculator).collidedWithAGhost(player, ghost);
        Mockito.verify(player).setAlive(false);
        Mockito.verify(player).setKiller(ghost);
    }

    @DisplayName("鬼碰到玩家")
    @Order(2)
    @Test
    public void testGhostCollidingWithPlayer() {
        Player player = mock(Player.class);
        Ghost ghost = mock(Ghost.class);

        Mockito.when(player.isAlive()).thenReturn(true);
        Mockito.doNothing().when(player).setAlive(false);

        collisions.collide(ghost, player);

        Mockito.verify(calculator).collidedWithAGhost(player, ghost);
        Mockito.verify(player).setAlive(false);
        Mockito.verify(player).setKiller(ghost);
    }

    @DisplayName("玩家碰到豆子")
    @Order(3)
    @Test
    public void testPlayerCollidingWithPellet() {
        Player player = mock(Player.class);
        Pellet pellet = mock(Pellet.class);

        collisions.collide(player, pellet);

        Mockito.verify(calculator).consumedAPellet(player, pellet);
        Mockito.verify(pellet).leaveSquare();
    }

    @DisplayName("豆子碰到玩家")
    @Order(4)
    @Test
    public void testPelletCollidingWithPlayer() {
        Player player = mock(Player.class);
        Pellet pellet = mock(Pellet.class);

        collisions.collide(pellet, player);

        Mockito.verify(calculator).consumedAPellet(player, pellet);
        Mockito.verify(pellet).leaveSquare();
    }



    @DisplayName("鬼碰到豆子")
    @Order(5)
    @Test
    public void testGhostCollidingWithPellet() {
        Ghost ghost = mock(Ghost.class);
        Pellet pellet = mock(Pellet.class);

        collisions.collide(ghost, pellet);

        Mockito.verifyNoInteractions(calculator);
        Mockito.verifyNoInteractions(pellet);
        Mockito.verifyNoInteractions(ghost);
    }

    @DisplayName("豆子碰到鬼")
    @Order(6)
    @Test
    public void testPelletCollidingWithGhost() {
        Ghost ghost = mock(Ghost.class);
        Pellet pellet = mock(Pellet.class);

        collisions.collide(pellet, ghost);

        Mockito.verifyNoInteractions(calculator);
        Mockito.verifyNoInteractions(pellet);
        Mockito.verifyNoInteractions(ghost);
    }

    @Test
    @DisplayName("鬼撞鬼")
    @Order(7)
    public void testGhostCollidingWithGhost(){
        Player player = mock(Player.class);
        Ghost ghost = mock(Ghost.class);
        Pellet pellet = mock(Pellet.class);
        collisions.collide(ghost,ghost);

        Mockito.verifyNoInteractions(calculator);
        Mockito.verifyNoInteractions(ghost);
    }





}
