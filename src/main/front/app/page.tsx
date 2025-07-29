"use client"

import React, { useState, useEffect } from 'react';
import { Tabs, TabsContent, TabsList, TabsTrigger } from "./components/ui/tabs";
import { Input } from "./components/ui/input";
import { Button } from "./components/ui/button";
import { Card, CardContent, CardHeader, CardTitle } from "./components/ui/card";
import { Badge } from "./components/ui/badge";
import { Avatar, AvatarFallback, AvatarImage } from "./components/ui/avatar";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "./components/ui/table";
import { Switch } from "./components/ui/switch";
import { Label } from "./components/ui/label";
import { Search, Heart, TrendingUp, TrendingDown, User, Wallet, Star, Clock, Mail, Lock, LogOut, TrendingUp as LogoIcon } from 'lucide-react';

import { loginUsuario } from './services/authService';
import { saveToken, logout } from './utils/auth';

// Mock data para ações
const mockStocks = [
  { ticker: 'PETR4', name: 'Petrobras', price: 32.45, change: 2.1, changePercent: 6.92 },
  { ticker: 'VALE3', name: 'Vale', price: 68.23, change: -1.45, changePercent: -2.08 },
  { ticker: 'ITUB4', name: 'Itaú Unibanco', price: 25.67, change: 0.78, changePercent: 3.13 },
  { ticker: 'BBDC4', name: 'Bradesco', price: 15.89, change: -0.23, changePercent: -1.43 },
  { ticker: 'ABEV3', name: 'Ambev', price: 11.34, change: 0.45, changePercent: 4.13 },
  { ticker: 'WEGE3', name: 'WEG', price: 45.78, change: 1.23, changePercent: 2.76 },
  { ticker: 'MGLU3', name: 'Magazine Luiza', price: 8.45, change: -0.67, changePercent: -7.34 },
  { ticker: 'B3SA3', name: 'B3', price: 12.56, change: 0.34, changePercent: 2.78 }
];

// Mock data para carteira
const mockPortfolio = [
  { ticker: 'PETR4', quantity: 100, avgPrice: 28.50, currentPrice: 32.45, pnl: 395.00 },
  { ticker: 'VALE3', quantity: 50, avgPrice: 70.00, currentPrice: 68.23, pnl: -88.50 },
  { ticker: 'ITUB4', quantity: 200, avgPrice: 24.00, currentPrice: 25.67, pnl: 334.00 },
  { ticker: 'WEGE3', quantity: 30, avgPrice: 42.00, currentPrice: 45.78, pnl: 113.40 }
];

// Login Component
const LoginScreen = ({ onLogin, darkMode, setDarkMode }: { onLogin: (login: string, password: string) => void, darkMode: boolean, setDarkMode: (dark: boolean) => void }) => {
  const [login, setLogin] = useState('');
  const [password, setPassword] = useState('');
  const [isLoading, setIsLoading] = useState(false);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try{
      const token = await loginUsuario({login,password});
      saveToken(token);
      onLogin(login, password)
    }catch(error){
      alert("Erro ao fazer login")
    }
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-slate-100 dark:from-slate-900 dark:to-blue-950 flex items-center justify-center p-4">
      <div className="absolute top-4 right-4">
        <div className="flex items-center space-x-2">
          <span className="text-sm text-muted-foreground">Modo Escuro</span>
          <Switch checked={darkMode} onCheckedChange={setDarkMode} />
        </div>
      </div>

      <Card className="w-full max-w-md border-0 bg-card/80 backdrop-blur-sm shadow-2xl">
        <CardHeader className="text-center space-y-4 pt-8">
          <div className="flex justify-center">
            <div className="h-16 w-16 bg-gradient-to-br from-blue-500 to-blue-600 rounded-2xl flex items-center justify-center">
              <LogoIcon className="h-8 w-8 text-white" />
            </div>
          </div>
          <div className="space-y-2">
            <h1 className="text-2xl">InvestApp</h1>
            <p className="text-muted-foreground">
              Faça login para acessar sua carteira
            </p>
          </div>
        </CardHeader>
        
        <CardContent className="space-y-6 pb-8">
          <form onSubmit={handleSubmit} className="space-y-4">
            <div className="space-y-2">
              <Label htmlFor="text">Login</Label>
              <div className="relative">
                <Mail className="absolute left-3 top-3 h-4 w-4 text-muted-foreground" />
                <Input
                  id="login"
                  type="text"
                  placeholder="Seu login"
                  value={login}
                  onChange={(e) => setLogin(e.target.value)}
                  className="pl-10 bg-background/50"
                  required
                />
              </div>
            </div>
            
            <div className="space-y-2">
              <Label htmlFor="password">Senha</Label>
              <div className="relative">
                <Lock className="absolute left-3 top-3 h-4 w-4 text-muted-foreground" />
                <Input
                  id="password"
                  type="password"
                  placeholder="Sua senha"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  className="pl-10 bg-background/50"
                  required
                />
              </div>
            </div>
            
            <Button 
              type="submit" 
              className="w-full bg-gradient-to-r from-blue-500 to-blue-600 hover:from-blue-600 hover:to-blue-700"
              disabled={isLoading}
            >
              {isLoading ? 'Entrando...' : 'Entrar'}
            </Button>
          </form>
          
          <div className="space-y-4">
            <div className="text-center">
              <Button 
                variant="link" 
                className="text-sm text-blue-600 hover:text-blue-700 dark:text-blue-400 dark:hover:text-blue-300 p-0"
                onClick={() => alert('Funcionalidade em desenvolvimento')}
              >
                Esqueci minha senha
              </Button>
            </div>
            
            <div className="relative">
              <div className="absolute inset-0 flex items-center">
                <div className="w-full border-t" />
              </div>
              <div className="relative flex justify-center text-xs uppercase">
                <span className="bg-card px-2 text-muted-foreground">
                  Ou
                </span>
              </div>
            </div>
            
            <Button 
              variant="outline" 
              className="w-full bg-background/50"
              onClick={() => alert('Funcionalidade em desenvolvimento')}
            >
              Criar conta
            </Button>
          </div>
          
        </CardContent>
      </Card>
    </div>
  );
};

export default function App() {
  const [darkMode, setDarkMode] = useState(false);
  const [searchTerm, setSearchTerm] = useState('');
  const [favorites, setFavorites] = useState<string[]>([]);
  const [searchHistory, setSearchHistory] = useState<string[]>([]);
  const [filteredStocks, setFilteredStocks] = useState(mockStocks);
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [user, setUser] = useState<{ login: string; name: string } | null>(null);

  // Toggle dark mode
  useEffect(() => {
    if (darkMode) {
      document.documentElement.classList.add('dark');
    } else {
      document.documentElement.classList.remove('dark');
    }
  }, [darkMode]);

  // Load favorites and search history from localStorage
  useEffect(() => {
    const savedFavorites = localStorage.getItem('favorites');
    const savedHistory = localStorage.getItem('searchHistory');
    const savedAuth = localStorage.getItem('isAuthenticated');
    const savedUser = localStorage.getItem('user');
    
    if (savedFavorites) {
      setFavorites(JSON.parse(savedFavorites));
    }
    if (savedHistory) {
      setSearchHistory(JSON.parse(savedHistory));
    }
    if (savedAuth === 'true' && savedUser) {
      setIsAuthenticated(true);
      setUser(JSON.parse(savedUser));
    }
  }, []);

  // Filter stocks based on search
  useEffect(() => {
    if (searchTerm) {
      const filtered = mockStocks.filter(stock => 
        stock.ticker.toLowerCase().includes(searchTerm.toLowerCase()) ||
        stock.name.toLowerCase().includes(searchTerm.toLowerCase())
      );
      setFilteredStocks(filtered);
    } else {
      setFilteredStocks(mockStocks);
    }
  }, [searchTerm]);

  const handleLogin = (login: string, password: string) => {
    const userData = {
      login,
      name: login || 'Usuário'
    };

    localStorage.setItem("isAuthenticated", "true");
    localStorage.setItem("user", JSON.stringify(userData));
    
    setUser(userData);
    setIsAuthenticated(true);
  };

  const handleLogout = () => {
    localStorage.removeItem("token")
    localStorage.removeItem("isAuthenticated");
    localStorage.removeItem("user");

    setIsAuthenticated(false);
    setUser(null);
  };

  const handleSearch = (term: string) => {
    setSearchTerm(term);
    if (term && !searchHistory.includes(term)) {
      const newHistory = [term, ...searchHistory.slice(0, 9)];
      setSearchHistory(newHistory);
      localStorage.setItem('searchHistory', JSON.stringify(newHistory));
    }
  };

  const toggleFavorite = (ticker: string) => {
    const newFavorites = favorites.includes(ticker)
      ? favorites.filter(fav => fav !== ticker)
      : [...favorites, ticker];
    
    setFavorites(newFavorites);
    localStorage.setItem('favorites', JSON.stringify(newFavorites));
  };

  const formatCurrency = (value: number) => {
    return new Intl.NumberFormat('pt-BR', {
      style: 'currency',
      currency: 'BRL'
    }).format(value);
  };

  const formatPercent = (value: number) => {
    return `${value > 0 ? '+' : ''}${value.toFixed(2)}%`;
  };

  const StockCard = ({ stock }: { stock: typeof mockStocks[0] }) => (
    <Card className="hover:shadow-lg transition-shadow duration-200 border-0 bg-card/50 backdrop-blur-sm">
      <CardHeader className="pb-3">
        <div className="flex justify-between items-start">
          <div>
            <CardTitle className="text-lg">{stock.ticker}</CardTitle>
            <p className="text-muted-foreground text-sm">{stock.name}</p>
          </div>
          <Button
            variant="ghost"
            size="sm"
            onClick={() => toggleFavorite(stock.ticker)}
            className="h-8 w-8 p-0"
          >
            <Heart
              className={`h-4 w-4 ${
                favorites.includes(stock.ticker)
                  ? 'fill-red-500 text-red-500'
                  : 'text-muted-foreground'
              }`}
            />
          </Button>
        </div>
      </CardHeader>
      <CardContent className="pt-0">
        <div className="space-y-2">
          <div className="flex justify-between items-center">
            <span className="text-2xl font-medium">{formatCurrency(stock.price)}</span>
          </div>
          <div className="flex items-center space-x-2">
            {stock.change > 0 ? (
              <TrendingUp className="h-4 w-4 text-green-500" />
            ) : (
              <TrendingDown className="h-4 w-4 text-red-500" />
            )}
            <Badge
              variant={stock.change > 0 ? "default" : "destructive"}
              className={stock.change > 0 ? "bg-green-100 text-green-800 dark:bg-green-900 dark:text-green-200" : ""}
            >
              {formatCurrency(stock.change)} ({formatPercent(stock.changePercent)})
            </Badge>
          </div>
        </div>
      </CardContent>
    </Card>
  );

  // Se não está autenticado, mostra tela de login
  if (!isAuthenticated) {
    return <LoginScreen onLogin={handleLogin} darkMode={darkMode} setDarkMode={setDarkMode} />;
  }

  // App principal para usuários autenticados
  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-slate-100 dark:from-slate-900 dark:to-blue-950">
      <div className="container mx-auto p-4 max-w-6xl">
        {/* Header */}
        <div className="flex justify-between items-center mb-6">
          <div className="flex items-center space-x-3">
            <div className="h-10 w-10 bg-gradient-to-br from-blue-500 to-blue-600 rounded-xl flex items-center justify-center">
              <LogoIcon className="h-5 w-5 text-white" />
            </div>
            <h1 className="text-3xl">InvestApp</h1>
          </div>
          <div className="flex items-center space-x-4">
            <div className="flex items-center space-x-2">
              <span className="text-sm text-muted-foreground">Modo Escuro</span>
              <Switch checked={darkMode} onCheckedChange={setDarkMode} />
            </div>
            <div className="flex items-center space-x-2">
              <span className="text-sm text-muted-foreground">Olá, {user?.name}</span>
              <Button variant="ghost" size="sm" onClick={handleLogout}>
                <LogOut className="h-4 w-4" />
              </Button>
            </div>
          </div>
        </div>

        <Tabs defaultValue="search" className="w-full">
          <TabsList className="grid w-full grid-cols-3 mb-6">
            <TabsTrigger value="search" className="flex items-center space-x-2">
              <Search className="h-4 w-4" />
              <span>Buscar</span>
            </TabsTrigger>
            <TabsTrigger value="portfolio" className="flex items-center space-x-2">
              <Wallet className="h-4 w-4" />
              <span>Carteira</span>
            </TabsTrigger>
            <TabsTrigger value="profile" className="flex items-center space-x-2">
              <User className="h-4 w-4" />
              <span>Perfil</span>
            </TabsTrigger>
          </TabsList>

          {/* Search Tab */}
          <TabsContent value="search" className="space-y-4">
            <Card className="border-0 bg-card/50 backdrop-blur-sm">
              <CardHeader>
                <CardTitle className="flex items-center space-x-2">
                  <Search className="h-5 w-5" />
                  <span>Buscar Ações</span>
                </CardTitle>
              </CardHeader>
              <CardContent>
                <div className="relative">
                  <Input
                    placeholder="Digite o ticker ou nome da empresa..."
                    value={searchTerm}
                    onChange={(e) => handleSearch(e.target.value)}
                    className="pl-10 bg-background/50"
                  />
                  <Search className="absolute left-3 top-3 h-4 w-4 text-muted-foreground" />
                </div>
              </CardContent>
            </Card>

            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-4">
              {filteredStocks.map((stock) => (
                <StockCard key={stock.ticker} stock={stock} />
              ))}
            </div>

            {filteredStocks.length === 0 && searchTerm && (
              <Card className="border-0 bg-card/30 backdrop-blur-sm">
                <CardContent className="text-center py-8">
                  <p className="text-muted-foreground">
                    Nenhuma ação encontrada para "{searchTerm}"
                  </p>
                </CardContent>
              </Card>
            )}
          </TabsContent>

          {/* Portfolio Tab */}
          <TabsContent value="portfolio" className="space-y-4">
            <Card className="border-0 bg-card/30 backdrop-blur-sm">
              <CardHeader>
                <CardTitle className="flex items-center space-x-2">
                  <Wallet className="h-5 w-5" />
                  <span>Minha Carteira</span>
                </CardTitle>
              </CardHeader>
              <CardContent>
                <div className="rounded-lg border bg-background/50">
                  <Table>
                    <TableHeader>
                      <TableRow>
                        <TableHead>Ticker</TableHead>
                        <TableHead>Quantidade</TableHead>
                        <TableHead>Preço Médio</TableHead>
                        <TableHead>Preço Atual</TableHead>
                        <TableHead>P&L</TableHead>
                      </TableRow>
                    </TableHeader>
                    <TableBody>
                      {mockPortfolio.map((position) => (
                        <TableRow key={position.ticker}>
                          <TableCell>{position.ticker}</TableCell>
                          <TableCell>{position.quantity}</TableCell>
                          <TableCell>{formatCurrency(position.avgPrice)}</TableCell>
                          <TableCell>{formatCurrency(position.currentPrice)}</TableCell>
                          <TableCell>
                            <Badge
                              variant={position.pnl > 0 ? "default" : "destructive"}
                              className={position.pnl > 0 ? "bg-green-100 text-green-800 dark:bg-green-900 dark:text-green-200" : ""}
                            >
                              {formatCurrency(position.pnl)}
                            </Badge>
                          </TableCell>
                        </TableRow>
                      ))}
                    </TableBody>
                  </Table>
                </div>

                <div className="mt-4 p-4 rounded-lg bg-muted/50">
                  <div className="flex justify-between items-center">
                    <span>Total P&L:</span>
                    <Badge
                      variant="default"
                      className="bg-green-100 text-green-800 dark:bg-green-900 dark:text-green-200 text-base px-3 py-1"
                    >
                      {formatCurrency(mockPortfolio.reduce((sum, pos) => sum + pos.pnl, 0))}
                    </Badge>
                  </div>
                </div>
              </CardContent>
            </Card>
          </TabsContent>

          {/* Profile Tab */}
          <TabsContent value="profile" className="space-y-4">
            <Card className="border-0 bg-card/30 backdrop-blur-sm">
              <CardHeader>
                <CardTitle className="flex items-center space-x-2">
                  <User className="h-5 w-5" />
                  <span>Meu Perfil</span>
                </CardTitle>
              </CardHeader>
              <CardContent className="space-y-6">
                <div className="flex items-center space-x-4">
                  <Avatar className="h-16 w-16">
                    <AvatarImage src="/placeholder-avatar.jpg" />
                    <AvatarFallback className="text-lg bg-blue-100 dark:bg-blue-900">
                      {user?.name.charAt(0).toUpperCase()}
                    </AvatarFallback>
                  </Avatar>
                  <div>
                    <h3 className="text-xl">{user?.name}</h3>
                    <p className="text-muted-foreground">{user?.login}</p>
                  </div>
                </div>

                <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                  <Card className="border bg-background/50">
                    <CardHeader className="pb-3">
                      <CardTitle className="flex items-center space-x-2 text-base">
                        <Star className="h-4 w-4" />
                        <span>Favoritos</span>
                      </CardTitle>
                    </CardHeader>
                    <CardContent>
                      {favorites.length > 0 ? (
                        <div className="space-y-2">
                          {favorites.map((ticker) => (
                            <div key={ticker} className="flex items-center justify-between p-2 rounded bg-muted/50">
                              <span>{ticker}</span>
                              <Button
                                variant="ghost"
                                size="sm"
                                onClick={() => toggleFavorite(ticker)}
                                className="h-6 w-6 p-0"
                              >
                                <Heart className="h-3 w-3 fill-red-500 text-red-500" />
                              </Button>
                            </div>
                          ))}
                        </div>
                      ) : (
                        <p className="text-muted-foreground text-sm">Nenhum favorito adicionado</p>
                      )}
                    </CardContent>
                  </Card>

                  <Card className="border bg-background/50">
                    <CardHeader className="pb-3">
                      <CardTitle className="flex items-center space-x-2 text-base">
                        <Clock className="h-4 w-4" />
                        <span>Histórico de Buscas</span>
                      </CardTitle>
                    </CardHeader>
                    <CardContent>
                      {searchHistory.length > 0 ? (
                        <div className="space-y-2">
                          {searchHistory.slice(0, 5).map((term, index) => (
                            <div key={index} className="flex items-center justify-between p-2 rounded bg-muted/50">
                              <span className="text-sm">{term}</span>
                              <Button
                                variant="ghost"
                                size="sm"
                                onClick={() => setSearchTerm(term)}
                                className="text-xs"
                              >
                                Buscar
                              </Button>
                            </div>
                          ))}
                        </div>
                      ) : (
                        <p className="text-muted-foreground text-sm">Nenhuma busca realizada</p>
                      )}
                    </CardContent>
                  </Card>
                </div>
              </CardContent>
            </Card>
          </TabsContent>
        </Tabs>
      </div>
    </div>
  );
}