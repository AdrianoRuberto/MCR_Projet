package spells;

public enum Element {
	FIRE {
		@Override
		public Element getStrong() {
			return Element.LEAF;
		}

		@Override
		public Element getWeak() {
			return Element.WATER;
		}
	},
	WATER {
		@Override
		public Element getStrong() {
			return Element.FIRE;
		}

		@Override
		public Element getWeak() {
			return Element.THUNDER;
		}
	},
	LEAF {
		@Override
		public Element getStrong() {
			return Element.ROCK;
		}

		@Override
		public Element getWeak() {
			return Element.FIRE;
		}
	},
	THUNDER {
		@Override
		public Element getStrong() {
			return Element.WATER;
		}

		@Override
		public Element getWeak() {
			return Element.ROCK;
		}
	},
	ROCK {
		@Override
		public Element getStrong() {
			return Element.THUNDER;
		}

		@Override
		public Element getWeak() {
			return Element.LEAF;
		}
	},
	NORMAL {
		@Override
		public Element getStrong() {
			return null;
		}

		@Override
		public Element getWeak() {
			return null;
		}
	};

	private Element strong, weak;

	public abstract Element getStrong();
	public abstract Element getWeak();
}




