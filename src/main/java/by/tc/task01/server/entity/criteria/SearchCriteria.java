package by.tc.task01.server.entity.criteria;

public final class SearchCriteria {

	public static enum Client {
		name("name"),
		password("password"),
		allowance("allowance");
		private String enumName;

		Client(String enumName) {
			this.enumName = enumName;
		}

		public String getEnumName(){
			return enumName;
		};

		public static String getCriteriaName(){
			return "Allowance";
		}

	}

	private SearchCriteria() {}
}

