import java.util.HashMap;

public class EffectLibrary {

	private HashMap<String, Effect> effects;

	public void initialise() {
		effects = new HashMap<>();

		registerEffect("grayScale", new GrayScaleEffect());
	}

	public boolean registerEffect(String name, Effect effect) {
		effects.put(name, effect);
		return true;
	}

	public boolean deregisterEffect(String name) {
		effects.remove(name);
		return true;
	}

	public Effect getEffect(String name) {
		return effects.get(name);
	}

	public String[] availableEffects() {
		return effects.keySet().toArray(new String[0]);
	}
}
