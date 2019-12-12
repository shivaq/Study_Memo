■■■■■■■■■■■■■■■■■■■■■■■■■■ RDF
・Turtle 言語は、RDF データを human-readable なフォーマットにしたもの。
・Apache Jena などのツールは、必要に応じて異なるRDFフォーマットを自動変換する。

-------------------------------------------------
<rdf:RDF xmlns="urn:example:"
xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#">
<Location rdf:nodeID="idaho">
  <name>Idaho</name>
  <type>state</type>
    <within>
        <Location rdf:nodeID="usa">
          <name>United States</name>
          <type>country</type>
          <within>
            <Location rdf:nodeID="namerica">
              <name>North America</name>
              <type>continent</type>
            </Location>
          </within>
        </Location>
    </within>
</Location>

  <Person rdf:nodeID="lucy">
    <name>Lucy</name>
    <bornIn rdf:nodeID="idaho"/>
  </Person>
</rdf:RDF>
-------------------------------------------------




▼ triple の主語、述語、対象はしばしば URI で記述される。
▼ なんでこんな書き方にしているか
・internet-wide data exchange のためにデザインされている
・あなたのデータを他の人のデータと接続できなければならないから

・下記書き方のおかげで、もし他の人が「within or lives_in」といった単語に異なる意味を付与したとしても、コンフリクトは起きない。

・あなたの「within or lives_in」
<http://my-company.com/namespace#within> or <http://my-company.com/namespace#lives_in>
・他の人の「within or lives_in」
<http://other.org/foo#within> and <http://other.org/foo#lives_in>.


▼ 下記URI から、RDF は何も解決できないし、する必要もない。これはただの名前空間に過ぎない。
・この、名前解決のための prefix は、ファイルのトップのあたりで指定するだけでいい。その後は気にせず下記進んでくれればいい。
<http://my-company.com/namespace>
※ ここの例では、「http:// なURL」 との混同を避けるため、解決できないURI(urn:example:within)を使用している。
