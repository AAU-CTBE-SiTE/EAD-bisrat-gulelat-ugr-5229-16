package com.craft;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("studentPU");

        EntityManager em = emf.createEntityManager();

        try {

            // 1. CREATE - Insert a new student


            System.out.println("\n===== CREATE =====");

            Student student = new Student();
            student.setName("Abebe Bekele");
            student.setTech("Java");

            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();

            int studentId = student.getId();

            System.out.println("Student created successfully!");
            System.out.println(student);



            // 2. READ - Find the student by ID


            System.out.println("\n===== READ =====");

            Student foundStudent =
                    em.find(Student.class, studentId);

            if (foundStudent != null) {
                System.out.println("Student found:");
                System.out.println(foundStudent);
            } else {
                System.out.println("Student not found!");
            }



            // 3. UPDATE - Change the student's technology


            System.out.println("\n===== UPDATE =====");

            em.getTransaction().begin();

            Student studentToUpdate =
                    em.find(Student.class, studentId);

            if (studentToUpdate != null) {
                studentToUpdate.setTech("Python");
            }

            em.getTransaction().commit();

            Student updatedStudent =
                    em.find(Student.class, studentId);

            System.out.println("Student updated successfully!");
            System.out.println(updatedStudent);



            // 4. DELETE - Remove the student


            System.out.println("\n===== DELETE =====");

            em.getTransaction().begin();

            Student studentToDelete =
                    em.find(Student.class, studentId);

            if (studentToDelete != null) {
                em.remove(studentToDelete);
            }

            em.getTransaction().commit();

            System.out.println("Student deleted successfully!");



            Student deletedStudent =
                    em.find(Student.class, studentId);

            if (deletedStudent == null) {
                System.out.println(
                        "Verification successful: student no longer exists."
                );
            }

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            e.printStackTrace();

        } finally {
            em.close();
            emf.close();
        }
    }
}