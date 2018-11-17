package cn.com.jonpad.weibotopsummary.to;

import java.util.List;

/**
 * @author Jon Chan
 * @date 2018/11/17 16:51
 */
public class Bing {

    /**
     * images : [{"startdate":"20181115","fullstartdate":"201811151600","enddate":"20181116","url":"/az/hprichbg/rb/ChiribiqueteNP_ZH-CN10719426351_1920x1080.jpg","urlbase":"/az/hprichbg/rb/ChiribiqueteNP_ZH-CN10719426351","copyright":"奇里比克特山脉国家自然公园的古代岩画，哥伦比亚 (© Steve Winter/Getty Images)","copyrightlink":"http://www.bing.com/search?q=%E5%A5%87%E9%87%8C%E6%AF%94%E5%85%8B%E7%89%B9%E5%B1%B1%E8%84%88%E5%9B%BD%E5%AE%B6%E8%87%AA%E7%84%B6%E5%85%AC%E5%9B%AD&form=hpcapt&mkt=zh-cn","title":"","quiz":"/search?q=Bing+homepage+quiz&filters=WQOskey:%22HPQuiz_20181115_ChiribiqueteNP%22&FORM=HPQUIZ","wp":true,"hsh":"b7ce2cad53348d324ad8b04e30952408","drk":1,"top":1,"bot":1,"hs":[]},{"startdate":"20181114","fullstartdate":"201811141600","enddate":"20181115","url":"/az/hprichbg/rb/EcolaSP_ZH-CN10746626161_1920x1080.jpg","urlbase":"/az/hprichbg/rb/EcolaSP_ZH-CN10746626161","copyright":"刘易斯和克拉克国家历史公园，美国俄勒冈州 (© Morey Milbradt/Alamy)","copyrightlink":"http://www.bing.com/search?q=%E5%88%98%E6%98%93%E6%96%AF%E5%92%8C%E5%85%8B%E6%8B%89%E5%85%8B%E5%9B%BD%E5%AE%B6%E5%8E%86%E5%8F%B2%E5%85%AC%E5%9B%AD&form=hpcapt&mkt=zh-cn","title":"","quiz":"/search?q=Bing+homepage+quiz&filters=WQOskey:%22HPQuiz_20181114_EcolaSP%22&FORM=HPQUIZ","wp":true,"hsh":"8e86d9d7d3704d658928a98d297a5768","drk":1,"top":1,"bot":1,"hs":[]}]
     * tooltips : {"loading":"正在加载...","previous":"上一个图像","next":"下一个图像","walle":"此图片不能下载用作壁纸。","walls":"下载今日美图。仅限用作桌面壁纸。"}
     */

    private TooltipsBean tooltips;
    private List<ImagesBean> images;

    public TooltipsBean getTooltips() {
        return tooltips;
    }

    public void setTooltips(TooltipsBean tooltips) {
        this.tooltips = tooltips;
    }

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }

    public static class TooltipsBean {
        /**
         * loading : 正在加载...
         * previous : 上一个图像
         * next : 下一个图像
         * walle : 此图片不能下载用作壁纸。
         * walls : 下载今日美图。仅限用作桌面壁纸。
         */

        private String loading;
        private String previous;
        private String next;
        private String walle;
        private String walls;

        public String getLoading() {
            return loading;
        }

        public void setLoading(String loading) {
            this.loading = loading;
        }

        public String getPrevious() {
            return previous;
        }

        public void setPrevious(String previous) {
            this.previous = previous;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public String getWalle() {
            return walle;
        }

        public void setWalle(String walle) {
            this.walle = walle;
        }

        public String getWalls() {
            return walls;
        }

        public void setWalls(String walls) {
            this.walls = walls;
        }
    }

    public static class ImagesBean {
        /**
         * startdate : 20181115
         * fullstartdate : 201811151600
         * enddate : 20181116
         * url : /az/hprichbg/rb/ChiribiqueteNP_ZH-CN10719426351_1920x1080.jpg
         * urlbase : /az/hprichbg/rb/ChiribiqueteNP_ZH-CN10719426351
         * copyright : 奇里比克特山脉国家自然公园的古代岩画，哥伦比亚 (© Steve Winter/Getty Images)
         * copyrightlink : http://www.bing.com/search?q=%E5%A5%87%E9%87%8C%E6%AF%94%E5%85%8B%E7%89%B9%E5%B1%B1%E8%84%88%E5%9B%BD%E5%AE%B6%E8%87%AA%E7%84%B6%E5%85%AC%E5%9B%AD&form=hpcapt&mkt=zh-cn
         * title :
         * quiz : /search?q=Bing+homepage+quiz&filters=WQOskey:%22HPQuiz_20181115_ChiribiqueteNP%22&FORM=HPQUIZ
         * wp : true
         * hsh : b7ce2cad53348d324ad8b04e30952408
         * drk : 1
         * top : 1
         * bot : 1
         * hs : []
         */

        private String startdate;
        private String fullstartdate;
        private String enddate;
        private String url;
        private String urlbase;
        private String copyright;
        private String copyrightlink;
        private String title;
        private String quiz;
        private boolean wp;
        private String hsh;
        private int drk;
        private int top;
        private int bot;
        private List<?> hs;

        public String getStartdate() {
            return startdate;
        }

        public void setStartdate(String startdate) {
            this.startdate = startdate;
        }

        public String getFullstartdate() {
            return fullstartdate;
        }

        public void setFullstartdate(String fullstartdate) {
            this.fullstartdate = fullstartdate;
        }

        public String getEnddate() {
            return enddate;
        }

        public void setEnddate(String enddate) {
            this.enddate = enddate;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrlbase() {
            return urlbase;
        }

        public void setUrlbase(String urlbase) {
            this.urlbase = urlbase;
        }

        public String getCopyright() {
            return copyright;
        }

        public void setCopyright(String copyright) {
            this.copyright = copyright;
        }

        public String getCopyrightlink() {
            return copyrightlink;
        }

        public void setCopyrightlink(String copyrightlink) {
            this.copyrightlink = copyrightlink;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getQuiz() {
            return quiz;
        }

        public void setQuiz(String quiz) {
            this.quiz = quiz;
        }

        public boolean isWp() {
            return wp;
        }

        public void setWp(boolean wp) {
            this.wp = wp;
        }

        public String getHsh() {
            return hsh;
        }

        public void setHsh(String hsh) {
            this.hsh = hsh;
        }

        public int getDrk() {
            return drk;
        }

        public void setDrk(int drk) {
            this.drk = drk;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public int getBot() {
            return bot;
        }

        public void setBot(int bot) {
            this.bot = bot;
        }

        public List<?> getHs() {
            return hs;
        }

        public void setHs(List<?> hs) {
            this.hs = hs;
        }
    }
}
