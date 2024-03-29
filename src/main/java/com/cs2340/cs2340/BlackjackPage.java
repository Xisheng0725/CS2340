package com.cs2340.cs2340;

import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * Creates the menu and interface for the Blackjack game.
 */
public class BlackjackPage {
    private static final int CARD_HEIGHT = 250;
    private static final int CARD_WIDTH = (int)(CARD_HEIGHT * 0.65);

    private Pane pane;
    private Scene scene;
    private Stage primaryStage;
    private MainPage mainPage;

    private BlackjackGame blackjackGame = new BlackjackGame();

    private Hand hand = new Hand();

    private Text dealerHandValue;
    private Text playerHandValue;

    public void formatGameScreen(Scene bjGameScene, Pane pane, Stage primaryStage, MainPage mainPage) {
        this.pane = pane;
        scene = bjGameScene;
        Image image = new Image("cursor.png");
        bjGameScene.setCursor(new ImageCursor(image));
        this.primaryStage = primaryStage;
        this.mainPage = mainPage;

        // Background
        ImageView gameBg = MainPage.getImageView("bg_total_placeholder.png", 800, 1200);
        pane.getChildren().add(gameBg);


        // Return button
        Button backBtn = new Button("Return");
        makeGlow((backBtn));
        MainPage.setButton(backBtn);
        backBtn.setTranslateX(30);
        backBtn.setTranslateY(750);
        pane.getChildren().add(backBtn);
        backBtn.setOnAction(e -> {
            primaryStage.setScene(mainPage.getSelectScene());
            reset2();
        });

        // Center card pile
        ImageView cardStack = MainPage.getImageView("back.png", CARD_HEIGHT, CARD_WIDTH);
        cardStack.relocate(600 - CARD_WIDTH / 2, 200);
        pane.getChildren().add(cardStack);

        // Hit button
        boolean check = true;
        ImageView hit = MainPage.getImageView("hit.png", 180, 180);
        hit.relocate(400, 570);
        makeGlow(hit);
        hit.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            // find whether it's the dealer's turn and pass in as parameter
           // if (check) {
                hit();
           // }
        });

        // Stand button
        ImageView stand = MainPage.getImageView("stand.png", 180, 180);
        stand.relocate(630, 570);
        makeGlow(stand);

        stand.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            AIDrawCard(true);
        });

        Font valueFont = new Font("verdana", 64);
        Paint color = new Color(.7, .7, .7, 1);

        // Dealer hand value
        dealerHandValue = new Text(405, 320, "0");
        dealerHandValue.setFont(valueFont);
        dealerHandValue.setFill(color);

        // Player hand value
        playerHandValue = new Text(845, 320, "0");
        playerHandValue.setFont(valueFont);
        playerHandValue.setFill(color);

        pane.getChildren().addAll(hit, stand, dealerHandValue, playerHandValue);
        initialGame(blackjackGame.getDealerHand(), blackjackGame.getPlayerHand());
    }

    private void hit() {
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // only draw a card if the game isn't won/lost/tied
         if (blackjackGame.getPlayerHand().getValue() < 21) {
             drawCard(false);
         } else {
             AIDrawCard(true);
         }
    }

    private void drawCard(boolean dealer) {
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // should be the size of the player/dealer's hand
        // (i.e. if hand has 3 cards, index should be 3 so it slides to the correct position)
        int index = blackjackGame.getPlayerHand().getHand().size();

        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // should call Blackjack to draw a card for the player/dealer

        int drawnCard = blackjackGame.hit(); // get the drawn card

        if (dealer) {
            // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            // set dealer hand value text to game logic's value for it
            dealerHandValue.setText(String.valueOf(blackjackGame.getDealerHand().getValue())); // blackjackLogic.getDealerHandValue or something
        } else {
            // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            // set player hand value text to game logic's value for it
            if (blackjackGame.getPlayerHand().getValue() >= 21) {
                AIDrawCard(true);
            }
            playerHandValue.setText(String.valueOf(blackjackGame.getPlayerHand().getValue()));
        }
        createAndAnimateCard(drawnCard, dealer, index);
    }

    private void AIDrawCard(boolean dealer) {
        blackjackGame.stand();
        int total = 0;
        for (int index = 0; index < blackjackGame.getDealerHand().getHand().size(); index++) {
            int drawCard = blackjackGame.getDealerHand().getHand().get(index).getValue();
            total += drawCard;
            dealerHandValue.setText(String.valueOf(total));
            if (index >= 2) {
                createAndAnimateCard(drawCard, dealer, index);
            }
        }
        String check;
        if (blackjackGame.won()) {
            check = "won.PNG";
        } else if (blackjackGame.tie()) {
            check = "tie.PNG";
        } else {
            check = "lose.png";
        }
        BJEndPage(check, mainPage.bjGamePane, dealer);
    }

    private void initialGame(Hand DealerHand, Hand PlayerHand) {
        for (int i = 0; i < 2; i++) {
            createAndAnimateCard(PlayerHand.getHand().get(i).getValue(), false, i);
            createAndAnimateCard(DealerHand.getHand().get(i).getValue(), true, i);
        }
        setUpHands(PlayerHand.getValue(), DealerHand.getValue());
    }

    private void setUpHands(int player, int dealer) {
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // iterate through initial player hand, calling createAndAnimateCard on each one
        // do the same for the entire dealer hand (original 2 cards + whatever else they draw)
        // set the hand value texts to the correct values
        playerHandValue.setText(String.valueOf(player));
        dealerHandValue.setText(String.valueOf(dealer));
    }

    private void createAndAnimateCard(int face, boolean dealer, int indexInHand) {
        var card = getCardImage(face);
        var endPosition = getCardPosition(dealer, indexInHand);
        pane.getChildren().add(card);
        TranslateTransition trans = new TranslateTransition(Duration.millis(700), card);
        trans.setFromX(600 - CARD_WIDTH / 2);
        trans.setFromY(200);
        trans.setToX(endPosition.getX());
        trans.setToY(endPosition.getY());
        trans.play();
    }

    public void reset(boolean dealer) {
        pane = new Pane();
        scene.setRoot(pane);
        blackjackGame.getDealerHand().getHand().clear();
        blackjackGame.getPlayerHand().getHand().clear();
        blackjackGame = new BlackjackGame();
        dealer = false;
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // reset the blackjacklogic variable here

        formatGameScreen(scene, pane, primaryStage, mainPage);
    }

    public void reset2() {
        pane = new Pane();
        scene.setRoot(pane);
        blackjackGame.getDealerHand().getHand().clear();
        blackjackGame.getPlayerHand().getHand().clear();
        blackjackGame = new BlackjackGame();
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // reset the blackjacklogic variable here

        formatGameScreen(scene, pane, primaryStage, mainPage);
    }


    public void BJEndPage(String indicator, Pane BJGamePane, boolean dealer) {
        String cssStyle = " -fx-text-fill: #006464;\n" +
                "    -fx-background-color: #DFB951;\n" +
                "    -fx-border-radius: 30;\n" +
                "    -fx-background-radius: 30;\n" +
                "    -fx-padding: 10;\n" +
                "    -fx-font-size:40;";
        ImageView BJOutcome = mainPage.getImageView(indicator, 400, 500);;
        Button BJReturnBtn = new Button("Return");
        makeGlow(BJReturnBtn);
        BJReturnBtn.setStyle(cssStyle);
        BJReturnBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                reset(dealer);
                primaryStage.setScene(mainPage.getSelectScene());
            }
        });

        HBox BJOptions = new HBox();

        //Setup replay button
        Button replayBtn = new Button("Replay");
        makeGlow(replayBtn);
        replayBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                formatGameScreen(mainPage.bjGameScene, pane, primaryStage, mainPage);
                reset(dealer);
            }
        });
        replayBtn.setStyle(cssStyle);
        BJOptions.getChildren().addAll(replayBtn, BJReturnBtn);
        BJOptions.setSpacing(40);
        BJOptions.setTranslateX(475);
        BJOptions.setTranslateY(500);
        BJOutcome.setTranslateY(70);
        BJOutcome.setTranslateX(300);

        for(Node child : BJGamePane.getChildren()) {
            child.setOpacity(0.5);
        }

        BJGamePane.getChildren().addAll(BJOutcome, BJOptions);
    }

    /**
     * Gets the position of a card in a hand.
     * @param dealer whether this card is in the dealer's hand (true) or player's hand (false)
     * @param index the index of the card
     * @return the position of the card in the GUI
     */
    private Point2D getCardPosition(boolean dealer, int index) {
        Point2D offset;
        if (dealer)
            offset = new Point2D(20, 20);
        else
            offset = new Point2D(980, 20);
        int cardsPerColumn = 6;
        int cardSeparationY = 70;
        int cardSeparationX = 50;
        return offset.add(new Point2D(
                (index / cardsPerColumn) * cardSeparationX,
                index % cardsPerColumn * cardSeparationY + (index / cardsPerColumn) * cardSeparationY / 2
        ));
    }

    /**
     * Gets the card image according to the card's face index.
     * @param face the card's face index. 1 = ace ... 13 = king.
     * @return the ImageView of the card.
     */
    private ImageView getCardImage(int face) {
        String fileName;
        switch (face) {
            case 1:
                fileName = "A"; break;
            case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9: case 10:
                fileName = "" + face; break;
            case 11:
                fileName = "J"; break;
            case 12:
                fileName = "Q"; break;
            case 13:
                fileName = "K"; break;
            default:
                fileName = "back";
        }
        return MainPage.getImageView(fileName + ".png", CARD_HEIGHT, CARD_WIDTH);
    }

    public void makeGlow(Node node) {
        double glowAmount = 0.5;
        Glow glow = new Glow();
        glow.setLevel(0);
        node.setEffect(glow);
        node.setOnMouseEntered(e -> {
            glow.setLevel(glowAmount);
        });
        node.setOnMouseExited(e -> {
            glow.setLevel(0);
        });

    }
}