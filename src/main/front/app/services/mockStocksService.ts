import { fetchStockByTicker } from '../services/tickerService';

export let mockStocks = [
  { ticker: "PETR4", name: "Petrobras", price: 0, change: 0, changePercent: 0 },
  { ticker: "VALE3", name: "Vale", price: 0, change: 0, changePercent: 0 },
  { ticker: "ITUB4", name: "Itaú Unibanco", price: 0, change: 0, changePercent: 0 },
  { ticker: "BBDC4", name: "Bradesco", price: 0, change: 0, changePercent: 0 },
  { ticker: "ABEV3", name: "Ambev", price: 0, change: 0, changePercent: 0 },
  { ticker: "WEGE3", name: "WEG", price: 0, change: 0, changePercent: 0 },
  { ticker: "MGLU3", name: "Magazine Luiza", price: 0, change: 0, changePercent: 0 },
  { ticker: "B3SA3", name: "B3", price: 0, change: 0, changePercent: 0 },
];

export const atualizarMockStocksComAPI = async () => {
  const atualizados = await Promise.all(
    mockStocks.map(async (item) => {
      try {
        const dados = await fetchStockByTicker(item.ticker);
        return {
          ...item,
          price: dados.regularMarketPrice,
          change: dados.regularMarketChange,
          changePercent: dados.regularMarketChangePercent
        };
      } catch (err) {
        console.error(`Erro ao buscar ${item.ticker}`, err);
        return item; // mantém valores antigos se erro
      }
    })
  );

  mockStocks = atualizados; // ← sobrescreve o array global
};