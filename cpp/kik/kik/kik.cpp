#include <iostream>
#include <cstdlib>
#include <stdlib.h>
#include <ctime>
#include <string>
#include "kik.h"

using namespace std;

POLE kik::plansza(int i)
{
    return _plansza[i/3][i%3];
}

void kik::plansza(int i, POLE arg)
{
    _plansza[i/3][i%3] = arg;
}

STAN kik::stan()
{
    return _stan;
}

void kik::stan(STAN arg)
{
    _stan = arg;
}

ZNAK kik::gracz()
{
    return _gracz;
}

void kik::gracz(ZNAK arg)
{
    _gracz = arg;
}

ILOSC kik::ilosc_graczy()
{
    return _ilosc_graczy;
}

void kik::ilosc_graczy(ILOSC arg)
{
    _ilosc_graczy = arg;
}

POZIOM kik::poziom()
{
    return _poziom;
}

void kik::poziom(POZIOM arg)
{
    _poziom = arg;
}

ZNAK kik::znak_gracza()
{
    return _znak_gracza;
}

void kik::znak_gracza(ZNAK arg)
{
    _znak_gracza = arg;
}

kik::kik()
{
    srand(time(NULL));

    for (int i=0; i<9; i++)
        plansza(i, PL_PUSTE);

    system("clear");

    ile_graczy();

    if (ilosc_graczy() == 1)
        czym_grasz();

    kto_zaczyna();

    if (ilosc_graczy() == 1)
        jaki_poziom();

    sprawdz_stan();

    drukuj_plansze(0);

    rozgrywka();
}

kik::~kik()
{
    cout << endl << "Dziękuję za wspólną grę :)" << endl;
}

void kik::ile_graczy()
{
    while (true)
    {
        string liczba;
        cout << "Ile graczy? (1, 2) ";
        cin >> liczba;

        if (liczba == "1")
        {
            ilosc_graczy(IL_JEDEN);
            return;
        }
        if (liczba == "2")
        {
            ilosc_graczy(IL_DWA);
            return;
        }
    }
}

void kik::czym_grasz()
{
    while (true)
    {
        string liczba;
        cout << "Czym grasz? (1-kolkiem, 2-krzyzykiem, 3-losowo) ";
        cin >> liczba;

        if (liczba == "1")
        {
            znak_gracza(ZN_KOLKO);
            return;
        }
        if (liczba == "2")
        {
            znak_gracza(ZN_KRZYZYK);
            return;
        }
        if (liczba == "3")
        {
            ZNAK los = (rand() % 2 ? ZN_KOLKO : ZN_KRZYZYK);
            cout << "Wylosowano " << static_cast<char>(los) << endl;
            znak_gracza(los);
            return;
        }
    }
}

void kik::kto_zaczyna()
{
    while (true)
    {
        string liczba;
        cout << "Kto zaczyna? (1-kolko, 2-krzyzyk, 3-losowo) ";
        cin >> liczba;

        if (liczba == "1")
        {
            gracz(ZN_KOLKO);
            return;
        }
        if (liczba == "2")
        {
            gracz(ZN_KRZYZYK);
            return;
        }
        if (liczba == "3")
        {
            ZNAK los = (rand() % 2 ? ZN_KOLKO : ZN_KRZYZYK);
            cout << "Wylosowano " << static_cast<char>(los) << endl;
            gracz(los);

            if (ilosc_graczy() == IL_DWA)
            {
                cin.ignore();
                cin.get();
            }

            return;
        }
    }
}

void kik::jaki_poziom()
{
    while (true)
    {
        string liczba;
        cout << "Jaki poziom? (1-latwy, 2-sredni, 3-trudny, 4-losowy) ";
        cin >> liczba;

        if (liczba == "1")
        {
            poziom(PZ_LATWY);
            return;
        }
        if (liczba == "2")
        {
            poziom(PZ_SREDNI);
            return;
        }
        if (liczba == "3")
        {
            poziom(PZ_TRUDNY);
            return;
        }
        if (liczba == "4")
        {
            POZIOM los;

            cout << "Wylosowano ";

            switch (rand() % 3)
            {
            case 0:
                los = PZ_LATWY;
                cout << "latwy";
                break;
            case 1:
                los = PZ_SREDNI;
                cout << "sredni";
                break;
            case 2:
                los = PZ_TRUDNY;
                cout << "trudny";
                break;
            }
            poziom(los);

            cin.ignore();
            cin.get();

            return;
        }
    }
}

void kik::sprawdz_stan()
{
    const int LINIE[][3] =
    {
        {0, 1, 2}, {3, 4, 5}, {6, 7, 8},    //poziome
        {0, 3, 6}, {1, 4, 7}, {2, 5, 8},    //pionowe
        {0, 4, 8}, {2, 4, 6}                //ukośne
    };

    for (int i=0; i<8; i++)
    {
        if
        (
            plansza(LINIE[i][0]) == plansza(LINIE[i][1]) and
            plansza(LINIE[i][0]) == plansza(LINIE[i][2]) and
            plansza(LINIE[i][0]) != PL_PUSTE
        )
        {
            stan(ST_WYGRANA);
            return;
        }
    }

    int liczba_zapelnionych_pol = 0;

    for (int i=0; i<9; i++)
    {
        if (plansza(i) != PL_PUSTE)
            liczba_zapelnionych_pol++;
    }

    if (liczba_zapelnionych_pol == 9)
        stan(ST_REMIS);
    else
        stan(ST_ROZGRYWKA);
}

void kik::drukuj_plansze(int styl)
{
    system("clear");
    cout << endl << endl
         << "         KÓŁKO I KRZYŻYK" << endl
         << "     -----------------------" << endl << endl;
/*
     |     |
  1  |  2  |  3
_____|_____|_____
     |     |
  4  |  5  |  6
_____|_____|_____
     |     |
  7  |  8  |  9
     |     |
*/
    string spacje = "        ";
    string pusty = "     |     |     ";
    string dol = "_____|_____|_____";

    for (int i=0; i<9; i++)
    {
        if (i % 3 == 0)
            cout << spacje << pusty << endl << spacje;

        if (plansza(i) == PL_PUSTE)
        {
            if (styl == 0)
                cout << "  " << i + 1 << "  ";
            else
                cout << "  " << " " << "  ";
        }
        else
        {
            if (plansza(i) == PL_KOLKO)
                cout << "  \033[1;33m" << static_cast<char>(plansza(i)) << "\033[0;37m  ";
            if (plansza(i) == PL_KRZYZYK)
                cout << "  \033[1;34m" << static_cast<char>(plansza(i)) << "\033[0;37m  ";
        }

        if ((i + 1) % 3)
            cout << "|";
        else
            cout << endl << spacje << (i == 8 ? pusty : dol) << endl;
    }

    cout << endl;

    switch (stan())
    {
    case ST_ROZGRYWKA:
        cout << static_cast<char>(gracz()) << ": ";
        break;

    case ST_WYGRANA:
        cout << "Wygrały "
             << (gracz() == ZN_KOLKO ? "krzyżyki" : "kółka") << "!" << endl;
        break;

    case ST_REMIS:
        cout << "Remis" << endl;
    }

}

int kik::przyjmij_liczbe()
{
    while (true)
    {
        string in;
        cin >> in;
        int out;

        if (in == "1")
            out = 0;
        if (in == "2")
            out = 1;
        if (in == "3")
            out = 2;
        if (in == "4")
            out = 3;
        if (in == "5")
            out = 4;
        if (in == "6")
            out = 5;
        if (in == "7")
            out = 6;
        if (in == "8")
            out = 7;
        if (in == "9")
            out = 8;

        if (plansza(out) == PL_PUSTE)
            return out;
        else
            drukuj_plansze(0);
    }
}

int kik::losuj_liczbe()
{
    const int LINIE[][3] =
    {
        {0, 1, 2}, {3, 4, 5}, {6, 7, 8},    //poziome
        {0, 3, 6}, {1, 4, 7}, {2, 5, 8},    //pionowe
        {0, 4, 8}, {2, 4, 6}                //ukośne
    };

    int out;

    if (poziom() == PZ_LATWY)
    {
        while (true)
        {
            out = rand() % 9;

            if (plansza(out) == PL_PUSTE)
                break;
        }
    }
    if (poziom() == PZ_SREDNI)
    {
        for (int i=0; i<8; i++)
        {
            if (plansza(LINIE[i][0]) == plansza(LINIE[i][1]) and plansza(LINIE[i][2]) == PL_PUSTE)
            {
                out = LINIE[i][2];
                break;
            }
            if (plansza(LINIE[i][0]) == plansza(LINIE[i][2]) and plansza(LINIE[i][1]) == PL_PUSTE)
            {
                out = LINIE[i][1];
                break;
            }
            if (plansza(LINIE[i][1]) == plansza(LINIE[i][2]) and plansza(LINIE[i][0]) == PL_PUSTE)
            {
                out = LINIE[i][0];
                break;
            }
            if (i == 8)
            {
                while (true)
                {
                    out = rand() % 9;

                    if (plansza(out) == PL_PUSTE)
                        break;
                }
            }
        }
    }
    cout << out + 1;
    return out;
}


void kik::wykonaj_ruch(int liczba)
{
    plansza(liczba, static_cast<POLE>(gracz()));
}

void kik::zmiana_gracza()
{
    gracz(gracz() == ZN_KOLKO ? ZN_KRZYZYK : ZN_KOLKO);
}

void kik::rozgrywka()
{
    while (true)
    {
        int liczba;

        if (ilosc_graczy() == 1 and znak_gracza() != gracz())
            liczba = losuj_liczbe();
        else
            liczba = przyjmij_liczbe();

        wykonaj_ruch(liczba);
        sprawdz_stan();
        zmiana_gracza();
        drukuj_plansze();

        if (stan() == ST_ROZGRYWKA)
            continue;
        else
            break;
    }
}
