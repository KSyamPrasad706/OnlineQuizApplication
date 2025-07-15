package Model;

public class Quiz {
	private int quizId;
    private String title;
    private String description;

    public Quiz() {}

	public Quiz(int quizId, String title, String description) {
		super();
		this.quizId = quizId;
		this.title = title;
		this.description = description;
	}

	public Quiz(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    

}
