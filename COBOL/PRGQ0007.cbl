       IDENTIFICATION DIVISION.
       PROGRAM-ID. PRGQ0007.

       ENVIRONMENT DIVISION.
       INPUT-OUTPUT SECTION.

       FILE-CONTROL.
           SELECT STUDENT-VSAM-FILE ASSIGN TO 'STUDENT.VSAM'
               ORGANIZATION IS INDEXED
               ACCESS MODE IS RANDOM
               RECORD KEY IS STUDENT-VSAM-ID
               ALTERNATE KEY IS STUDENT-VSAM-INSERTDATE
               FILE STATUS IS FILE-CHECK-KEY.

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
           05 STUDENT-VSAM-INCLUSION-DATE   PIC 9(8).

           WORKING-STORAGE SECTION.

       01  FILE-STATUS      PIC XX.


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

       01  WS-USERINPUT.

           05 WS-INPUT-STUDENT-VSAM-ID               PIC 9(4).
           05 WS-SEPARATOR1-VSAM               PIC X VALUE ','.
           05 WS-INPUT-STUDENT-VSAM-NAME             PIC X(27).
           05 WS-SEPARATOR2-VSAM               PIC X VALUE ','.
           05 WS-INPUT-STUDENT-VSAM-DOB              PIC 9(8).
           05 WS-INPUT-SEPARATOR3-VSAM               PIC X VALUE ','.
           05 WS-INPUT-STUDENT-VSAM-COURSE           PIC X(15).
           05 WS-INPUT-SEPARATOR4-VSAM               PIC X VALUE ','.
           05 WS-INPUT-INSERTDATE               PIC 9(8).
           05 WS-INPUT-SEPARATOR5-VSAM               PIC X VALUE ','.
           05 WS-INPUT-UPDATEDATE               PIC 9(8).

       01  WS-WORK-AREAS.
           05  FILE-CHECK-KEY      PIC X(2).
           05  WS-STUDENT-COUNT    PIC 9(4)  VALUE 0.

           05 WS-DATE-INCLUSION PIC 9(8).
           05 WS-RECORD-COUNT   PIC 9(4) VALUE 0.
           05 WS-DISPLAY-COUNT  PIC 9(4) VALUE 0.
           05 WS-FORMATTED-DATE PIC X(10).


       01  ID-QUERY-HEADER.
           05 FILLER      PIC X VALUE '+'.
           05 FILLER      PIC X VALUE '-'
               OCCURS 46 TIMES.
           05 FILLER      PIC X VALUE '+'.

       01  ID-QUERY-HEADER2.
           05 FILLER      PIC X VALUE '|'.
           05 FILLER      PIC X(4) VALUE SPACES.
           05 TITRE11      PIC X(24) VALUE ' Q U E R Y  S T U D E N '.
           05 TITRE12      PIC X(14) VALUE 'T S  B Y  I D '.
           05 FILLER      PIC X(4) VALUE SPACES.
           05 FILLER      PIC X VALUE '|'.

       PROCEDURE DIVISION.
       0000-MAIN-PROCEDURE.
