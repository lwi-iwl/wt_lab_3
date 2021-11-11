package by.tc.task01.server.entity.criteria;

public final class SearchCriteria {

	public static enum Client {
		name("name"),
		password("password");
		private String enumName;

		Client(String enumName) {
			this.enumName = enumName;
		}

		public String getEnumName(){
			return enumName;
		};

		public static String getCriteriaName(){
			return "Client";
		}

	}

	public static enum Student{
		name("name"),
		averageScore("averageScore");
		private String enumName;

		Student(String enumName) {
			this.enumName = enumName;
		}

		public String getEnumName(){
			return enumName;
		};

		public static String getCriteriaName(){
			return "Client";
		}

	}

	private SearchCriteria() {}
}

