package au.com.carecareers.android.jobModule.applyJobModule.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nikesh on 12/8/2017.
 */

public class ApplyJobModel {
    public class ApplyJobRequest {

        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("resume_type")
        @Expose
        private String resumeType;
        @SerializedName("resume")
        @Expose
        private String resume;
        @SerializedName("cover_letter_type")
        @Expose
        private String coverLetterType;
        @SerializedName("cover_letter")
        @Expose
        private String coverLetter;
        @SerializedName("screening_questions")
        @Expose
        private List<ScreeningQuestion> screeningQuestions = null;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getResumeType() {
            return resumeType;
        }

        public void setResumeType(String resumeType) {
            this.resumeType = resumeType;
        }

        public String getResume() {
            return resume;
        }

        public void setResume(String resume) {
            this.resume = resume;
        }

        public String getCoverLetterType() {
            return coverLetterType;
        }

        public void setCoverLetterType(String coverLetterType) {
            this.coverLetterType = coverLetterType;
        }

        public String getCoverLetter() {
            return coverLetter;
        }

        public void setCoverLetter(String coverLetter) {
            this.coverLetter = coverLetter;
        }

        public List<ScreeningQuestion> getScreeningQuestions() {
            return screeningQuestions;
        }

        public void setScreeningQuestions(List<ScreeningQuestion> screeningQuestions) {
            this.screeningQuestions = screeningQuestions;
        }

        private class ScreeningQuestion {
            @SerializedName("question_id")
            @Expose
            private String questionId;
        }
    }
}
