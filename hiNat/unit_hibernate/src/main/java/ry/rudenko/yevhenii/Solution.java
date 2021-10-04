package ry.rudenko.yevhenii;


import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;
import ry.rudenko.yevhenii.entity.Course;
import ry.rudenko.yevhenii.entity.Group;
import ry.rudenko.yevhenii.entity.Lesson;
import ry.rudenko.yevhenii.entity.Student;
import ry.rudenko.yevhenii.entity.Teacher;
import ry.rudenko.yevhenii.entity.Theme;
import ry.rudenko.yevhenii.repository.StudentRepository;

public class Solution {
  public  void findNextLessonCommand(){
    StudentRepository studentRepository = new StudentRepository();
    final Student student = studentRepository.findById(1L);
    System.out.printf("|%-25s|%-15s|%-15s|%-15s|%-15s|\n","start of classes","teacher","Theme", "Group", "course");
    final Group group = student.getGroup();
    final Set<Lesson> lessons = student.getLessons();
    Lesson lessonFind = null;
    Instant startOfClasses = Instant.now().plusSeconds(9999999999L);
    for (Lesson lesson : lessons) {
      final Instant dateTime = lesson.getDateTime();
      final Instant dateTimeNow = Instant.now();
      if (dateTime.getEpochSecond() > dateTimeNow.getEpochSecond()){
        if (dateTime.getEpochSecond() < startOfClasses.getEpochSecond()){
          lessonFind = lesson;
          startOfClasses = dateTime;
        }
      }
    }
    assert lessonFind != null;
    final Teacher teacher = lessonFind.getTeacher();
    final Theme theme = lessonFind.getTheme();
    final Course course = theme.getCourse();
    System.out.printf("|%-25s|%-15s|%-15s|%-15s|%-15s|\n",
        Timestamp.from(startOfClasses),teacher.getFirstName() + " " + teacher.getLastName(),
        theme.getName(), group.getName(),course.getName());
    System.out.println();
  }

}
