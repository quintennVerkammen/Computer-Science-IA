package main;

public class Comment {
	String fullComment = "";
	
	String[] introA = {" has shown excellent work ethic and understanding of the subjects at hand throughout the semester. ",
			" is a strong student, having shown a lot of excellent analytical and thinking skills. ",
			" has consistently shown talent in the subjects covered. "};
	String[] introB = {" has shown good work ethic and understading of the subjects at hand throughout the semester. ",
			" has consistently shown a lot of good analytical and thinking skills. ",
			" shows good investment and understanding in the topics covered. "};
	String[] introC = {" could have shown better work ethic and more investment during the past semester. ",
			" could have shonw better critical thinking and analysis skills throughout the semester. ",
			" could have increased investment in the topics covered and in doing so their understanding of them. "};
	String[] introF = {" has shown poor work ethic and understanding of the topics covered during the past semester. ",
			" is a poor student, having shown little analytical and critical thinking skills. ",
			" has shown very poor participation in class and a lack of general investment. "};

	String[] suggestionA = {"I would suggest them to keep doing what they are doing as it clearly offers excellent results. ",
				"They should just continue doing the things they have been doing as the results speak for themselves. ",
				"Their current grade reflects their excellent effort and dedication throughougt the term. "};
	String[] suggestionB = {"I suggest they keep doing what they are doing, and with a little bit more effor they could achieve excellence. ",
				"I suggest that they continue on the path they are on and, in the future, this could lead to truly excellent grades. ",
				"With  very little extra effort, they could nudge themselves into having truly excellent grades. "};
	String[] suggestionC = {"I suggest that they step up their efforts next semester to achieve what they know they are truly capable of. ",
				"With a little bit more effort in class, they could easily improve their grades. ",
				"I suggest spending a bit more time reviewing the subjects covered in class, as this could really help them improve. "};
	String[] suggestionF = {"They need to start focusing and really putting in the work in class, as this grade does not reflect what i know they are truly capable of. ",
				"I suggest spending extra time reviewing topics covered in class to catch up and truly achieve what I know them to be able to. ",
				"I need them to seek more help from their peers and myself to clarify any doubts they have to improve their grades. "};

	String[] improvementA = {"No improvement is needed, I simply need them to keep doing what they are doing to maintain their excellent grades. ",
				"I am available at all times to offer even further help and guidance although I do not think that is required for this student. ",
				"As their grade can hardly be improved upon, they now need to focus on maintaining such high standards and the excellence they have shown. "};
	String[] improvementB = {"This student has great potential, and with a bit more work, I am confident that they can achieve even better grades. ",
				"If they would seek a bit more help from myself and their peers, I know they could achieve even higher grades. ",
				"With a bit more dedication, they can realize their potential and achieve extremely high grades. "};
	String[] improvementC = {"Their grade is not reflective of their potential, but with some more work, this potential can be truly realized. ",
				"I am available to offer any help or guidance for them to benefit from to truly level up their grades. ",
				"I encourage them to seek more help to achieve the grades that they should be getting. "};
	String[] improvementF = {"Improvement is strongly needed, and I expect to see this in the coming semesters. ",
				"During the next term, I expect to see engagement and initiative from this student for them to get the grades that they want. ",
				"Over the course of the next term, significant improvement is expected as I know they can improve their grades. "};

	String[] conclusionA = {"For now there is nothing left to say but to wish you a nice holiday!",
				"I look forward to seeing their streak of excellence continue over the coming semester!",
				"I hope you have a great holiday and I expect this student's fantastic performance to continue over the coming semester!"};
	String[] conclusionB = {"If this student could review what we have covered in class over the holidays, I am sure their grade would improve instantly!",
				"I hope you enjoy your holidays!",
				"In conclusion, a tiny bit more work would turn their grades from simply good to excellent."};
	String[] conclusionC = {"I would encourage them to work on class topics over the holiday to improve their grades over the coming semester.",
				"I hope they do some reviewing over the holiday as this would help them greatly in achieving their true potential.",
				"With the right mentality, I know this student can improve their grades over the coming semester, and I hope they enjoy their holidays!"};
	String[] conclusionF = {"I expect them to review classwork over the holidays to be able to start the new semester with a refreshed mentality.",
				"I need them to review content they did not understand in class over the coming holidays to start at equal footing with other students in the next semester.",
				"Work is needed to catch up on the content they had difficulties with, and I expect this to happen over the holidays."};
	
	public String generateComment(double percentage) {

        StringBuilder commentBuilder = new StringBuilder();

        if (percentage >= 0 && percentage <= 60) {
            commentBuilder.append(introA[(int) Math.random() * 3]);
            commentBuilder.append(suggestionA[(int) Math.random() * 3]);
            commentBuilder.append(improvementA[(int) Math.random() * 3]);
            commentBuilder.append(conclusionA[(int) Math.random() * 3]);
        } else if (percentage > 60 && percentage <= 75) {
            commentBuilder.append(introB[(int) Math.random() * 3]);
            commentBuilder.append(suggestionB[(int) Math.random() * 3]);
            commentBuilder.append(improvementB[(int) Math.random() * 3]);
            commentBuilder.append(conclusionB[(int) Math.random() * 3]);
        } else if (percentage > 75 && percentage < 90) {
            commentBuilder.append(introC[(int) Math.random() * 3]);
            commentBuilder.append(suggestionC[(int) Math.random() * 3]);
            commentBuilder.append(improvementC[(int) Math.random() * 3]);
            commentBuilder.append(conclusionC[(int) Math.random() * 3]);
        } else if (percentage >= 90) {
            commentBuilder.append(introF[(int) Math.random() * 3]);
            commentBuilder.append(suggestionF[(int) Math.random() * 3]);
            commentBuilder.append(improvementF[(int) Math.random() * 3]);
            commentBuilder.append(conclusionF[(int) Math.random() * 3]);
        }
        
        return commentBuilder.toString();
    }
	
	public Comment(double percentage) {
		fullComment = generateComment(percentage);
	}
	
	public String getComment() {
		return fullComment;
	}
}
