int c3(int tab[])
{
	/*
		Funkcja zwraca najmniejszy dodatni element tablicy 100-elementowej,
		jezeli takowy nie istnieje to zwraca -1.
	*/

	int min = -1;

	for (int i=0; i!=100; ++i)
	{
		if (tab[i] > 0)
		{
			min = tab[i];
			break;
		}
	}
	
	for (int i=0; i!=100; ++i)
		if (tab[i] > 0 && tab[i] < min)
			min = tab[i];


	return min;
}


int d3(char tab[])
{
	int letters[26] = {0};
	int ile = 0;

	for (int i=0; i!=200; ++i){
		if (tab[i] >= 'a' && tab[i] <= 'z')
			++letters[tab[i]-'a'];
	}

	for (auto i: letters)
		if (i > 1)
			++ile;

	return ile;
}


void e3(int tab[])
{
	//indeksowo
	for (int i=19; i+1; --i)
		cout << tab[i] << endl;
	
	//wskaznikowo
	for (int *i=tab+19; i+1>tab; --i)
		cout << i* << endl;
}
