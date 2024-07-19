      ******************************************************************
      * Author:
      * Date:
      * Purpose:
      * Tectonics: cobc
      ******************************************************************
       IDENTIFICATION DIVISION.
       PROGRAM-ID. PRGR0008.
       ENVIRONMENT DIVISION.

       INPUT-OUTPUT SECTION.
       FILE-CONTROL.
           SELECT STUDENT-VSAM-FILE ASSIGN TO 'STUDENT.VSAM'
            FILE STATUS IS FILE-CHECK-KEY
               ORGANIZATION IS INDEXED
               ACCESS MODE IS DYNAMIC
               RECORD KEY IS STUDENT-VSAM-ID
               ALTERNATE KEY IS STUDENT-VSAM-COURSE
               WITH DUPLICATES.



       DATA DIVISION.
       FILE SECTION.
       FD STUDENT-VSAM-FILE.


       01  STUDENT-VSAM-RECORD.
           88 ENDOFFILE                     VALUE HIGH-VALUE.
           05 STUDENT-VSAM-ID               PIC 9(4).
           05 SEPARATOR1-VSAM               PIC X.
           05 STUDENT-VSAM-NAME             PIC X(27).
           05 SEPARATOR2-VSAM               PIC X.
           05 STUDENT-VSAM-DOB              PIC 9(8).
           05 SEPARATOR3-VSAM               PIC X.
           05 STUDENT-VSAM-COURSE           PIC X(15).
           05 SEPARATOR4-VSAM               PIC X VALUE ','.
           05 STUDENT-VSAM-INSERTDATE       PIC 9(8).
           05 SEPARATOR5-VSAM               PIC X VALUE ','.
           05 STUDENT-VSAM-UPDATEDATE       PIC 9(8).

       WORKING-STORAGE SECTION.

       01  FILE-STATUS   PIC XX.


       01  WS-STUDENT-VSAM-RECORD.
           05 WS-STUDENT-VSAM-ID               PIC 9(4).
           05 WS-SEPARATOR1-VSAM               PIC X.
           05 WS-STUDENT-VSAM-NAME             PIC X(27).
           05 WS-SEPARATOR2-VSAM               PIC X.
           05 WS-STUDENT-VSAM-DOB              PIC 9(8).
           05 WS-SEPARATOR3-VSAM               PIC X.
           05 WS-STUDENT-VSAM-COURSE           PIC X(15).
           05 WS-SEPARATOR4-VSAM               PIC X.
           05 WS-VSAM-INSERTDATE               PIC 9(8).
           05 WS-SEPARATOR5-VSAM               PIC X.
           05 WS-VSAM-UPDATEDATE               PIC 9(8).

       01  WS-WORK-AREAS.
           05  FILE-CHECK-KEY      PIC X(2).
           05  WS-STUDENT-COUNT    PIC 9(4)  VALUE 0.
           05 WS-CURRENT-COURSE   PIC X(15) VALUE SPACES.
           05 WS-PREV-COURSE      PIC X(15) VALUE SPACES.
           05 WS-TOTAL-STUDENTS  PIC 9(4) VALUE 0.

       PROCEDURE DIVISION.
       MAIN-PROCEDURE.

            PERFORM 1000-DISPLAY-HEADER.

           OPEN INPUT STUDENT-VSAM-FILE.

           READ STUDENT-VSAM-FILE INTO WS-STUDENT-VSAM-RECORD
               AT END SET ENDOFFILE TO TRUE.

           PERFORM 1100-PROCESS-RECORD UNTIL ENDOFFILE.

           PERFORM 1400-STOP-PROGRAM.

           STOP RUN.

       1000-DISPLAY-HEADER.
       DISPLAY '------------------------------------------------------'
               '-------------------------------------'
       DISPLAY '                                      CLASS REPORT    '
       DISPLAY '------------------------------------------------------'
               '-------------------------------------'.


       1100-PROCESS-RECORD.
           MOVE STUDENT-VSAM-COURSE TO WS-CURRENT-COURSE.

           IF WS-CURRENT-COURSE NOT EQUAL WS-PREV-COURSE
               IF WS-STUDENT-COUNT > 0
                   PERFORM 1300-DISPLAY-COURSE-FOOTER
               END-IF
               PERFORM 1200-DISPLAY-COURSE-HEADER
               MOVE WS-CURRENT-COURSE TO WS-PREV-COURSE
           END-IF


           DISPLAY WS-STUDENT-VSAM-ID
           " | "WS-STUDENT-VSAM-NAME
           " | "WS-STUDENT-VSAM-DOB
           " | "WS-VSAM-INSERTDATE
           "    | "WS-VSAM-UPDATEDATE.

           ADD 1 TO WS-STUDENT-COUNT.
           ADD 1 to WS-TOTAL-STUDENTS.

           READ STUDENT-VSAM-FILE INTO WS-STUDENT-VSAM-RECORD
               AT END SET ENDOFFILE TO TRUE.


       1200-DISPLAY-COURSE-HEADER.

       DISPLAY 'COURSE: ' WS-CURRENT-COURSE.

       DISPLAY '-------------------------------------------------------'
               '-------------------------------------'.
       DISPLAY ' ID   | STUDENT NAME                | BIRTHDAY |'
               'INSERT DATE | UPDATE DATE '.
       DISPLAY '-------------------------------------------------------'
               '-------------------------------------'.

       1300-DISPLAY-COURSE-FOOTER.
       DISPLAY '-------------------------------------------------'
                   '-----'
                '-------------------------------------'
           DISPLAY 'TOTAL STUDENTS : ' WS-TOTAL-STUDENTS.

       1400-STOP-PROGRAM.
           PERFORM 1300-DISPLAY-COURSE-FOOTER.
           PERFORM 1500-DISPLAY-FOOTER.
           CLOSE STUDENT-VSAM-FILE.

       1500-DISPLAY-FOOTER.


           STOP RUN.
       END PROGRAM PRGR0008.
