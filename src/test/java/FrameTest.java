import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FrameTest {
    
 private FrameFixture window;
    
    @Before
  public void setUp() {
    Gui frame = GuiActionRunner.execute(() -> new Gui());
    window = new FrameFixture(frame);
    window.show(); // shows the frame to test
  }

  @Test
  public void disabledButton() {
      window.button("buttonAdd").requireDisabled();
      window.textBox("keyTextField").enterText("2");
      window.button("buttonAdd").requireDisabled();
      window.textBox("valueTextField").enterText("44");
      window.button("buttonAdd").requireEnabled();
  }

  @Test
  public void testSearchButton() {
      window.button("buttonSearch").click();
      window.textBox("result").requireEmpty();
      window.textBox("keyTextField").enterText("2");
      window.button("buttonSearch").click();
      window.textBox("result").requireEmpty();
      window.textBox("valueTextField").enterText("44");
      window.button("buttonAdd").requireEnabled();
      window.button("buttonAdd").click();
      window.button("buttonSearch").click();
      window.textBox("result").requireText("1)44\n");
   }

    @Test
    public void testAddButton() {
        window.textBox("keyTextField").enterText("2");
        window.textBox("valueTextField").enterText("44");
        window.button("buttonAdd").click();
        window.button("buttonSearch").click();
        window.textBox("result").requireText("1)44\n");
        window.textBox("valueTextField").enterText("TEST");
        window.button("buttonAdd").click();
        window.textBox("valueTextField").enterText("00000");
        window.button("buttonAdd").click();
        window.textBox("keyTextField").enterText("20");
        window.textBox("valueTextField").enterText("44");
        window.button("buttonAdd").click();
        window.textBox("valueTextField").enterText("24");
        window.button("buttonAdd").click();
        window.button("buttonSearch").click();
        window.textBox("result").requireText("1)44\n2)24\n");
        window.textBox("valueTextField").enterText("0");
        window.button("buttonAdd").click();
        window.textBox("keyTextField").enterText("2");
        window.button("buttonSearch").click();
        window.textBox("result").requireText("1)44\n2)TEST\n3)00000\n");
        window.textBox("keyTextField").enterText("20");
        window.button("buttonSearch").click();
        window.textBox("result").requireText("1)44\n2)24\n3)0\n");

    }

    @Test
    public void canceldButton() {
        window.textBox("keyTextField").enterText("2");
        window.button("buttonCancel").click();
        window.textBox("keyTextField").requireEmpty();
        window.textBox("valueTextField").requireEmpty();
        window.textBox("result").requireEmpty();
        window.textBox("keyTextField").enterText("2");
        window.textBox("valueTextField").enterText("4");
        window.button("buttonCancel").click();
        window.textBox("keyTextField").requireEmpty();
        window.textBox("valueTextField").requireEmpty();
        window.textBox("result").requireEmpty();
        window.textBox("keyTextField").enterText("2");
        window.textBox("valueTextField").enterText("444");
        window.button("buttonAdd").click();
        window.button("buttonSearch").click();
        window.button("buttonCancel").click();
        window.textBox("keyTextField").requireEmpty();
        window.textBox("valueTextField").requireEmpty();
        window.textBox("result").requireEmpty();

    }

  @After
  public void tearDown() {
    window.cleanUp();
  }
    
}
