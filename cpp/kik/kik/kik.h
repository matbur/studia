#ifndef KIK_H
#define KIK_H

enum ZNAK
{
    ZN_KOLKO = 'O',
    ZN_KRZYZYK = 'X'
};
enum POLE
{
    PL_PUSTE = ' ',
    PL_KOLKO = ZN_KOLKO,
    PL_KRZYZYK = ZN_KRZYZYK
};
enum STAN
{
    ST_ROZGRYWKA,
    ST_WYGRANA,
    ST_REMIS
};
enum ILOSC
{
    IL_JEDEN = 1,
    IL_DWA
};
enum POZIOM
{
    PZ_LATWY,
    PZ_SREDNI,
    PZ_TRUDNY
};

class kik
{
public:
    kik();
    ~kik();

    void rozgrywka();

private:
    kik operator=(const kik&);
    kik(const kik&);

    void plansza(int, POLE);
    void stan(STAN);
    void gracz(ZNAK);
    void ilosc_graczy(ILOSC);
    void poziom(POZIOM);
    void znak_gracza(ZNAK);

    POLE plansza(int);
    STAN stan();
    ZNAK gracz();
    ILOSC ilosc_graczy();
    POZIOM poziom();
    ZNAK znak_gracza();

    POLE _plansza[3][3];
    STAN _stan;
    ZNAK _gracz;
    ILOSC _ilosc_graczy;
    POZIOM _poziom;
    ZNAK _znak_gracza;

    void ile_graczy();
    void czym_grasz();
    void kto_zaczyna();
    void jaki_poziom();
    void sprawdz_stan();
    int przyjmij_liczbe();
    int losuj_liczbe();
    void wykonaj_ruch(int);
    void zmiana_gracza();
    void drukuj_plansze(int=1);
};

#endif // KIK_H
