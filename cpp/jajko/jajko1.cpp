/*************************************************************************************/

//  Szkielet programu do tworzenia modelu sceny 3-D z wizualizacją osi
//  układu współrzednych

/*************************************************************************************/

#include <windows.h>
#include <iostream>
#include <gl/gl.h>
#include <gl/glut.h>
#include <cmath>
#include <cstdio>
#include <ctime>
#include <cstdlib>
#include <algorithm>
#include <vector>
#include <string>
#include <sstream>

# define M_PI 3.14159265358979323846  /* pi */

typedef float point3[3];
int model = 2;  // 0- punkty, 1- siatka, 2 - wypełnione trójkąty
int color = 0; // 0- losowo, 1- gradient poziomy, 2- gradient pionowy
int N = 20;
point3 **pointTab = nullptr;
point3 **colorTab = nullptr;
auto THETA = .2;
auto run = true;
float theta[] = {0.0, 0.0, 0.0}; // trzy kąty obrotu
const std::vector<std::string> menu = {
        "Instrukcja:",
        "p - punkty",
        "w - siatka",
        "s - trojkaty",
        "g - zmiana kolorow",
        "- - mniej punktow",
        "= - wiecej punktow",
        "[ - wolniej",
        "] - szybciej",
        "<space> - pauza",
        "q - wyjscie"
};

namespace std {
    template<typename T>
    std::string to_string(T t) {
        std::stringstream ss;
        std::string s;
        ss << t;
        ss >> s;
        return s;
    }
}

void clean_tables() {
    for (auto i = 0; i < N; i++) {
        delete[] pointTab[i];
        delete[] colorTab[i];
    }

    delete[] pointTab;
    pointTab = nullptr;

    delete[] colorTab;
    colorTab = nullptr;
}

void hsv2rgb(point3 out, float h, float s, float v) {
    float hh, p, q, t, ff;
    long i;

    hh = h;
    if (hh >= 360.0) hh = 0.0;
    hh /= 60.0;
    i = (long) hh;
    ff = hh - i;
    p = (float) (v * (1.0 - s));
    q = (float) (v * (1.0 - (s * ff)));
    t = (float) (v * (1.0 - (s * (1.0 - ff))));

    switch (i) {
        case 0:
            out[0] = v;
            out[1] = t;
            out[2] = p;
            break;
        case 1:
            out[0] = q;
            out[1] = v;
            out[2] = p;
            break;
        case 2:
            out[0] = p;
            out[1] = v;
            out[2] = t;
            break;

        case 3:
            out[0] = p;
            out[1] = q;
            out[2] = v;
            break;
        case 4:
            out[0] = t;
            out[1] = p;
            out[2] = v;
            break;
        case 5:
        default:
            out[0] = v;
            out[1] = p;
            out[2] = q;
            break;
    }
}

void printText(float x, float y, std::string str) {
    glRasterPos2f(x, y);
    for (auto i: str) {
        glutBitmapCharacter(GLUT_BITMAP_9_BY_15, i);
    }
}

void Axes(void) {

    point3 x_min = {-5.0, 0.0, 0.0};
    point3 x_max = {5.0, 0.0, 0.0};
    // początek i koniec obrazu osi x

    point3 y_min = {0.0, -5.0, 0.0};
    point3 y_max = {0.0, 5.0, 0.0};
    // początek i koniec obrazu osi y

    point3 z_min = {0.0, 0.0, -5.0};
    point3 z_max = {0.0, 0.0, 5.0};
    //  początek i koniec obrazu osi y

    glColor3f(1.0f, 0.0f, 0.0f);  // kolor rysowania osi - czerwony
    glBegin(GL_LINES); // rysowanie osi x

    glVertex3fv(x_min);
    glVertex3fv(x_max);

    glEnd();

    glColor3f(0.0f, 1.0f, 0.0f);  // kolor rysowania - zielony
    glBegin(GL_LINES);  // rysowanie osi y

    glVertex3fv(y_min);
    glVertex3fv(y_max);

    glEnd();

    glColor3f(0.0f, 0.0f, 1.0f);  // kolor rysowania - niebieski
    glBegin(GL_LINES); // rysowanie osi z

    glVertex3fv(z_min);
    glVertex3fv(z_max);

    glEnd();

}

void createColors() {
    colorTab = new point3 *[N];
    for (auto i = 0; i < N; i++) {
        colorTab[i] = new point3[N];
    }
}

void randomColors() {
    puts("random colors");

    for (auto i = 0; i < N; i++) {
        for (auto j = 0; j < N; j++) {
            colorTab[i][j][0] = (float) rand() / RAND_MAX;
            colorTab[i][j][1] = (float) rand() / RAND_MAX;
            colorTab[i][j][2] = (float) rand() / RAND_MAX;
        }
    }
    for (auto i = 0; i < N; i++) {
        colorTab[i][N - 1][0] = colorTab[N - 1 - i][0][0];
        colorTab[i][N - 1][1] = colorTab[N - 1 - i][0][1];
        colorTab[i][N - 1][2] = colorTab[N - 1 - i][0][2];
    }
}

void horizontalGradient() {
    puts("horizontal gradient");

    for (auto i = 0; i < N; i++) {
        auto direction = 2 * i < N;

        for (auto j = 0; j < N; j++) {
            if (direction)
                hsv2rgb(colorTab[i][j], 720.0f / N * (i), 1, 1);
            else
                hsv2rgb(colorTab[i][j], 720.0f / N * (N - 1 - i), 1, 1);
        }
    }
}

void verticalGradient() {
    puts("vertical gradient");

    for (auto i = 0; i < N; i++) {
        for (auto j = 0; j < N; j++) {
            hsv2rgb(colorTab[i][j], 360.0f / N * j, 1, 1);
        }
    }
}

void fillColors() {
    if (colorTab == nullptr) {
        createColors();
    }
    switch (color) {
        case 0:
            randomColors();
            break;
        case 1:
            horizontalGradient();
            break;
        case 2:
            verticalGradient();
            break;
        default:
            break;
    }
}

void calculateEgg() {
    puts("calculate egg");
    pointTab = new point3 *[N];

    for (auto i = 0; i < N; i++) {
        pointTab[i] = new point3[N];

        for (auto j = 0; j < N; j++) {
            auto u = (float) i / (N - 1);
            auto v = (float) j / (N - 1);

            auto common_part = -90 * pow(u, 5)
                               + 225 * pow(u, 4)
                               - 270 * pow(u, 3)
                               + 180 * pow(u, 2)
                               - 45 * u;

            pointTab[i][j][0] = (float) (common_part * cos(M_PI * v));
            pointTab[i][j][1] = (float) (160 * pow(u, 4)
                                         - 320 * pow(u, 3)
                                         + 160 * pow(u, 2));
            pointTab[i][j][2] = (float) (common_part * sin(M_PI * v));
        }
    }
}

void drawEggPoints() {
    glBegin(GL_POINTS);
    for (auto i = 0; i < N; i++) {
        for (auto j = 0; j < N; j++) {
            glVertex3fv(pointTab[i][j]);
        }
    }
    glEnd();
}

void drawEggLines() {
    glBegin(GL_LINES);
    for (auto i = 0; i < N; i++) {
        auto direction = 2 * (2 * i < N) - 1;
        for (auto j = 0; j < N - 1; j++) {
            // linie poziome
            glVertex3fv(pointTab[i][j]);
            glVertex3fv(pointTab[i][j + 1]);
            // linie pionowe
            glVertex3fv(pointTab[j][i]);
            glVertex3fv(pointTab[j + 1][i]);

            if (!i || i == N - 1) {
                continue;
            }
            // linie ukosne
            glVertex3fv(pointTab[i][j]);
            glVertex3fv(pointTab[i + direction][j + 1]);
        }
    }
    glEnd();
}

void drawEggTriangles() {
    glBegin(GL_TRIANGLES);
    for (auto i = 0; i < N - 1; i++) {
        auto direction = i < N / 2;

        for (auto j = 0; j < N - 1; j++) {
            // trojkat gorny
            glColor3fv(colorTab[i][j]);
            glVertex3fv(pointTab[i][j]);

            glColor3fv(colorTab[i][j + 1]);
            glVertex3fv(pointTab[i][j + 1]);

            glColor3fv(colorTab[i + 1][j + direction]);
            glVertex3fv(pointTab[i + 1][j + direction]);

            // trojkat dolny
            glColor3fv(colorTab[i + 1][j]);
            glVertex3fv(pointTab[i + 1][j]);

            glColor3fv(colorTab[i + 1][j + 1]);
            glVertex3fv(pointTab[i + 1][j + 1]);

            glColor3fv(colorTab[i][j + !direction]);
            glVertex3fv(pointTab[i][j + !direction]);
        }
    }
    glEnd();
}

void drawEgg() {
    if (pointTab == nullptr) {
        fillColors();
        calculateEgg();
    }

    switch (model) {
        case 0:
            drawEggPoints();
            break;
        case 1:
            drawEggLines();
            break;
        case 2:
            drawEggTriangles();
            break;

        default:
            break;
    }
}

void drawMenu() {
    glColor3f(0, 0, 0);
    for (auto i = 0; i < menu.size(); i++) {
        printText(10, -2.3f - .5f * i, menu[i]);
    }

    printText(-13, -7, "N = " + std::to_string(N));
    printText(-11, -7, "szybkosc = " + std::to_string(THETA * 100));
}

void RenderScene(void) {

    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    // Czyszczenie okna aktualnym kolorem czyszczącym

    glLoadIdentity();
    // Czyszczenie macierzy bieżącej

    drawMenu();

    Axes();

    glTranslatef(0, -5, 0);

    glRotatef(theta[0], 1.0, 0.0, 0.0);
    glRotatef(theta[1], 0.0, 1.0, 0.0);
    glRotatef(theta[2], 0.0, 0.0, 1.0);

    glColor3f(0, 0, 0);
    drawEgg();

    glFlush();
    // Przekazanie poleceń rysujących do wykonania

    glutSwapBuffers();
    //

}

void MyInit(void) {
    glClearColor(1, 1, 1, 1);
}

void ChangeSize(GLsizei horizontal, GLsizei vertical) {

    GLfloat AspectRatio;
    // Deklaracja zmiennej AspectRatio  określającej proporcję
    // wymiarów okna

    if (vertical == 0)  // Zabezpieczenie przed dzieleniem przez 0

        vertical = 1;

    glViewport(0, 0, horizontal, vertical);
    // Ustawienie wielkościokna okna widoku (viewport)
    // W tym przypadku od (0,0) do (horizontal, vertical)

    glMatrixMode(GL_PROJECTION);
    // Przełączenie macierzy bieżącej na macierz projekcji

    glLoadIdentity();
    // Czyszcznie macierzy bieżącej

    AspectRatio = (GLfloat) horizontal / (GLfloat) vertical;
    // Wyznaczenie współczynnika  proporcji okna
    // Gdy okno nie jest kwadratem wymagane jest określenie tak zwanej
    // przestrzeni ograniczającej pozwalającej zachować właściwe
    // proporcje rysowanego obiektu.
    // Do okreslenia przestrzeni ograniczjącej służy funkcja
    // glOrtho(...)

    if (horizontal <= vertical)

        glOrtho(-7.5, 7.5, -7.5 / AspectRatio, 7.5 / AspectRatio, 10.0, -10.0);

    else

        glOrtho(-7.5 * AspectRatio, 7.5 * AspectRatio, -7.5, 7.5, 10.0, -10.0);

    glMatrixMode(GL_MODELVIEW);
    // Przełączenie macierzy bieżącej na macierz widoku modelu

    glLoadIdentity();
    // Czyszcenie macierzy bieżącej

}

void spinEgg() {

    theta[0] -= THETA;
    if (theta[0] > 360.0) theta[0] -= 360.0;

    theta[1] -= THETA;
    if (theta[1] > 360.0) theta[1] -= 360.0;

    theta[2] -= THETA;
    if (theta[2] > 360.0) theta[2] -= 360.0;

    glutPostRedisplay(); //odświeżenie zawartości aktualnego okna
}

void keys(unsigned char key, int x, int y) {
    printf("key:|%c| THETA:|%.3f| N:|%d|\n", key, THETA, N);

    switch (key) {
        case 'p':
            model = 0;
            break;
        case 'w':
            model = 1;
            break;
        case 's':
            model = 2;
            break;
        case 'g':
            color = (color + 1) % 3;
            fillColors();
            break;
        case '[':
            THETA *= .95;
            break;
        case ']':
            THETA *= 1.05;
            break;
        case ' ':
            run = !run;
            THETA = run ? .2 : 0;
            break;
        case '=':
            clean_tables();
            N++;
            break;
        case '-':
            if (N > 2) {
                clean_tables();
                N--;
            }
            break;
        case 'q':
            exit(0);
        default:
            break;
    }

    RenderScene(); // przerysowanie obrazu sceny
}

int main(void) {
    srand((unsigned int) time(nullptr));

    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);

    const auto size = 900;
    glutInitWindowSize(1600, size);

    glutCreateWindow("Układ współrzędnych 3-D");

    glutDisplayFunc(RenderScene);
    // Określenie, że funkcja RenderScene będzie funkcją zwrotną
    // (callback function).  Bedzie ona wywoływana za każdym razem
    // gdy zajdzie potrzba przeryswania okna

    glutKeyboardFunc(keys);

    glutIdleFunc(spinEgg);

    glutReshapeFunc(ChangeSize);
    // Dla aktualnego okna ustala funkcję zwrotną odpowiedzialną
    // zazmiany rozmiaru okna

    MyInit();
    // Funkcja MyInit() (zdefiniowana powyżej) wykonuje wszelkie
    // inicjalizacje konieczne  przed przystąpieniem do renderowania

    glEnable(GL_DEPTH_TEST);
    // Włączenie mechanizmu usuwania powierzchni niewidocznych

    glutMainLoop();
    // Funkcja uruchamia szkielet biblioteki GLUT

}
